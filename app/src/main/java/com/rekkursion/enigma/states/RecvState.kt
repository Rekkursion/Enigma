package com.rekkursion.enigma.states

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.viewholders.BaseItemViewHolder

interface RecvState {
    // do on-click event
    fun doOnClick(stateContext: RecvStateContext, position: Int)

    // do on-long-click event
    fun doOnLongClick(stateContext: RecvStateContext, position: Int)

    // get the item-decoration
    fun getItemDecoration(context: Context): RecyclerView.ItemDecoration

    /* =================================================================== */

    // check if the position is the folder-item or not
    fun isFolderItem(stateContext: RecvStateContext, position: Int): Boolean =
        stateContext.recyclerView.adapter?.getItemViewType(position) == BaseItemViewHolder.BaseItemViewType.FOLDER_ITEM.ordinal

    // check if the position is the vocabulary-item-master or not
    fun isVocabularyItemMaster(stateContext: RecvStateContext, position: Int): Boolean =
        stateContext.recyclerView.adapter?.getItemViewType(position) == BaseItemViewHolder.BaseItemViewType.VOCABULARY_ITEM_MASTER.ordinal

    // check if the position is the vocabulary-item-slave or not
    fun isVocabularyItemSlave(stateContext: RecvStateContext, position: Int): Boolean =
        stateContext.recyclerView.adapter?.getItemViewType(position) == BaseItemViewHolder.BaseItemViewType.VOCABULARY_ITEM_SLAVE.ordinal
}