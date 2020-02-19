package com.rekkursion.enigma.states

import com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand.CertainItemEnterFolderCommand
import com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand.CertainItemExpandOrUnexpandCommand
import com.rekkursion.enigma.managers.CommandManager
import com.rekkursion.enigma.viewholders.BaseItemViewHolder

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

    }
}