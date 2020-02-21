package com.rekkursion.enigma.states

import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter
import com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand.CertainItemEnterFolderCommand
import com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand.CertainItemExpandOrUnexpandCommand
import com.rekkursion.enigma.commands.itemlistcommand.itemlistshowdialogcommand.ItemListShowDialogFolderItemCommand
import com.rekkursion.enigma.commands.itemlistcommand.itemlistshowdialogcommand.ItemListShowDialogVocabularyItemMasterCommand
import com.rekkursion.enigma.managers.CommandManager
import com.rekkursion.enigma.viewholders.BaseItemViewHolder

class GeneralRecvState private constructor(): RecvState {
    companion object {
        // the unique instance
        private val mInstance: GeneralRecvState = GeneralRecvState()
        // get the unique instance
        fun getInstance(): GeneralRecvState = mInstance
    }

    /* =================================================================== */

    override fun doOnClick(stateContext: RecvStateContext, position: Int) {
        when (stateContext.recyclerView.adapter?.getItemViewType(position)) {
            // folder-item
            BaseItemViewHolder.BaseItemViewType.FOLDER_ITEM.ordinal ->
                CommandManager.doCommand(CertainItemEnterFolderCommand::class, position)

            // vocabulary-item-master
            BaseItemViewHolder.BaseItemViewType.VOCABULARY_ITEM_MASTER.ordinal ->
                CommandManager.doCommand(CertainItemExpandOrUnexpandCommand::class, position)
        }
    }

    override fun doOnLongClick(stateContext: RecvStateContext, position: Int) {
        // the adapter of the recycler-view
        val adapter = stateContext.recyclerView.adapter as? ItemRecyclerViewAdapter ?: return

        // the view-type of the touched item
        val viewType = adapter.getItemViewType(position)

        if (viewType == BaseItemViewHolder.BaseItemViewType.FOLDER_ITEM.ordinal)
            CommandManager.doCommand(ItemListShowDialogFolderItemCommand::class, stateContext, position)
        else if (viewType == BaseItemViewHolder.BaseItemViewType.VOCABULARY_ITEM_MASTER.ordinal)
            CommandManager.doCommand(ItemListShowDialogVocabularyItemMasterCommand::class, stateContext, position)
    }

    override fun toString(): String = "GENERAL_STATE"
}