package com.rekkursion.enigma.managers

import com.rekkursion.enigma.commands.BaseCommand
import kotlin.reflect.KClass

object CommandManager {
    // the hash-map to store commands
    private val mCommands = HashMap<String, BaseCommand>()

    /* =================================================================== */

    // put or replace a new command
    fun putCommand(commandClass: KClass<*>, command: BaseCommand) {
        mCommands[getSelector(commandClass)] = command
    }

    // remove a command
    fun removeCommand(commandClass: KClass<*>) {
        if (hasCommand(commandClass))
            mCommands.remove(getSelector(commandClass))
    }

    // check if has a certain command or not
    fun hasCommand(commandClass: KClass<*>): Boolean = mCommands.containsKey(getSelector(commandClass))

    // do a command
    fun doCommand(commandClass: KClass<*>, vararg args: Any?) {
        mCommands[getSelector(commandClass)]?.execute(*args)
    }

    /* =================================================================== */

    // convert a command-class into a selector for selecting the command from the hash-map
    private fun getSelector(commandClass: KClass<*>): String = commandClass.java.name
}