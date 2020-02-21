package com.rekkursion.enigma.managers

import com.rekkursion.enigma.commands.BaseCommand
import com.rekkursion.enigma.enums.CommandType
import kotlin.reflect.KClass

object CommandManager {
    // the hash-map to store commands
    private val mCommands = HashMap<CommandType, BaseCommand>()

    /* =================================================================== */

    // put or replace a new command
    fun putCommand(commandType: CommandType, command: BaseCommand) {
        mCommands[commandType] = command
    }

    // remove a command
    fun removeCommand(commandType: CommandType) {
        if (hasCommand(commandType))
            mCommands.remove(commandType)
    }

    // check if has a certain command or not
    fun hasCommand(commandType: CommandType): Boolean = mCommands.containsKey(commandType)

    // do a command
    fun doCommand(commandType: CommandType, vararg args: Any?) {
        mCommands[commandType]?.execute(*args)
    }
}