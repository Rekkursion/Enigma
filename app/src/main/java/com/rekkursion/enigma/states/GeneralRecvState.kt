package com.rekkursion.enigma.states

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.enums.CommandType
import com.rekkursion.enigma.itemdecorations.GeneralItemListRecyclerViewItemDecoration
import com.rekkursion.enigma.managers.CommandManager

class GeneralRecvState private constructor(): RecvState {
    override fun doOnClick(stateContext: RecvStateContext, position: Int) {
        // folder-item
        if (isFolderItem(stateContext, position))
            CommandManager.doCommand(CommandType.CERTAIN_ITEM_ENTER_FOLDER, position)
        // vocabulary-item-master
        else if (isVocabularyItemMaster(stateContext, position))
            CommandManager.doCommand(CommandType.CERTAIN_ITEM_EXPAND_OR_UNEXPAND, position)
    }

    override fun doOnLongClick(stateContext: RecvStateContext, position: Int) {
        // folder-item
        if (isFolderItem(stateContext, position))
            CommandManager.doCommand(CommandType.ITEM_LIST_SHOW_DIALOG_FOLDER_ITEM, stateContext, position)
        // vocabulary-item-master
        else if (isVocabularyItemMaster(stateContext, position))
            CommandManager.doCommand(CommandType.ITEM_LIST_SHOW_DIALOG_VOCABULARY_ITEM_MASTER, stateContext, position)
    }

    override fun getItemDecoration(context: Context): RecyclerView.ItemDecoration =
        GeneralItemListRecyclerViewItemDecoration(context)

    override fun toString(): String = "GENERAL_STATE"

    /* =================================================================== */

    companion object {
        // the unique instance
        private val mInstance: GeneralRecvState = GeneralRecvState()
        // get the unique instance
        fun getInstance(): GeneralRecvState = mInstance
    }
}