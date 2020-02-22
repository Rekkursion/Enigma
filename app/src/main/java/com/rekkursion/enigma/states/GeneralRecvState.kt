package com.rekkursion.enigma.states

import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter
import com.rekkursion.enigma.enums.CommandType
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
                CommandManager.doCommand(CommandType.CERTAIN_ITEM_ENTER_FOLDER, position)

            // vocabulary-item-master
            BaseItemViewHolder.BaseItemViewType.VOCABULARY_ITEM_MASTER.ordinal ->
                CommandManager.doCommand(CommandType.CERTAIN_ITEM_EXPAND_OR_UNEXPAND, position)
        }
    }

    override fun doOnLongClick(stateContext: RecvStateContext, position: Int) {
        // the adapter of the recycler-view
        val adapter = stateContext.recyclerView.adapter as? ItemRecyclerViewAdapter ?: return

        // the view-type of the touched item
        val viewType = adapter.getItemViewType(position)

        if (viewType == BaseItemViewHolder.BaseItemViewType.FOLDER_ITEM.ordinal)
            CommandManager.doCommand(CommandType.ITEM_LIST_SHOW_DIALOG_FOLDER_ITEM, stateContext, position)
        else if (viewType == BaseItemViewHolder.BaseItemViewType.VOCABULARY_ITEM_MASTER.ordinal)
            CommandManager.doCommand(CommandType.ITEM_LIST_SHOW_DIALOG_VOCABULARY_ITEM_MASTER, stateContext, position)
    }

    override fun toString(): String = "GENERAL_STATE"
}