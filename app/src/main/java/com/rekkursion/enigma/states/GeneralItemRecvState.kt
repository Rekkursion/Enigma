package com.rekkursion.enigma.states

import android.app.AlertDialog
import android.util.Log
import android.view.View
import android.widget.Toast
import com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand.CertainItemEnterFolderCommand
import com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand.CertainItemExpandOrUnexpandCommand
import com.rekkursion.enigma.managers.CommandManager
import com.rekkursion.enigma.viewholders.BaseItemViewHolder
import com.rekkursion.enigma.views.ListDialog

class GeneralItemRecvState private constructor(): ItemRecvState {
    companion object {
        // the unique instance
        private val mInstance: GeneralItemRecvState = GeneralItemRecvState()

        // get the unique instance
        fun getInstance(): GeneralItemRecvState = mInstance
    }

    /* =================================================================== */

    override fun doOnClick(context: ItemRecvContext, position: Int) {
        when (context.recyclerView.adapter?.getItemViewType(position)) {
            // folder-item
            BaseItemViewHolder.BaseItemViewType.FOLDER_ITEM.ordinal ->
                CommandManager.doCommand(CertainItemEnterFolderCommand::class, position)

            // vocabulary-item-master
            BaseItemViewHolder.BaseItemViewType.VOCABULARY_ITEM_MASTER.ordinal ->
                CommandManager.doCommand(CertainItemExpandOrUnexpandCommand::class, position)
        }
    }

    override fun doOnLongClick(context: ItemRecvContext, position: Int) {
        // TODO: long-click on folder- or vocabulary- item
        ListDialog.Builder(context.getContext())
            .addListItem("YES", View.OnClickListener {
                AlertDialog.Builder(context.getContext()).setMessage(":3").create().show()
            })
            .create()
            .show()
    }
}