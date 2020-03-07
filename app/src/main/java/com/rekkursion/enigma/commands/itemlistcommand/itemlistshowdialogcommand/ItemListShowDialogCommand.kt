package com.rekkursion.enigma.commands.itemlistcommand.itemlistshowdialogcommand

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter
import com.rekkursion.enigma.commands.itemlistcommand.ItemListCommand
import com.rekkursion.enigma.states.RecvStateContext

abstract class ItemListShowDialogCommand(recyclerView: RecyclerView): ItemListCommand(recyclerView) {
    /**
     * @param args: varargs Any? {
     *      1. RecvStateContext: the recv-state-context
     *      2. Int: the position of the item in the recycler-view
     * }
     */
    override fun execute(vararg args: Any?) {
        val stateContext = args[0] as RecvStateContext
        val position = args[1] as Int

        // the adapter of the recycler-view
        val adapter = stateContext.recyclerView.adapter as? ItemRecyclerViewAdapter ?: return

        // the item to be operated
        val item = adapter.getBaseItemAndItsTruePosition(position).first

        // create the list-dialog and show it
        createListDialog(stateContext, item.getName(), position)?.show()
    }

    abstract fun createListDialog(stateContext: RecvStateContext, dialogTitle: String, position: Int): AlertDialog?
}