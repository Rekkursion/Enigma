package com.rekkursion.enigma.commands

interface BaseCommand {
    // execute the designated operation
    fun execute(vararg args: Any?)
}