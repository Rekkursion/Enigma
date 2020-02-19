package com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand

import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.commands.itemlistcommand.ItemListCommand

abstract class CertainItemCommand(recyclerView: RecyclerView): ItemListCommand(recyclerView) {
    /**
     * finalize the execute method whose parameter is a 'vararg Any?'
     *
     * @param args: varargs Any? {
     *      1. Int: the position of the recycler-view to be executed
     * }
     */
    final override fun execute(vararg args: Any?) {
        val position = args[0] as Int
        executeAt(position)
    }

    // and replace it with a method whose parameter is a single Int which means the position of the recycler-view
    abstract fun executeAt(position: Int)
}