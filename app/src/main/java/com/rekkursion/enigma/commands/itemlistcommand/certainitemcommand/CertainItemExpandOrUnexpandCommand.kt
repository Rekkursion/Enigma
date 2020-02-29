package com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand

import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter
import com.rekkursion.enigma.models.VocabularyItem

class CertainItemExpandOrUnexpandCommand(recyclerView: RecyclerView): CertainItemCommand(recyclerView) {
    /**
     * @param position: Int -> the position of the recycler-view to be executed
     * @param args: vararg Any? -> *no parameters*
     */
    override fun executeAt(position: Int, vararg args: Any?) {
        val baseItem = (mRecvItemList.adapter as? ItemRecyclerViewAdapter)?.getBaseItemAndItsTruePosition(position)?.first
        baseItem?.let {
            if (baseItem is VocabularyItem) {
                baseItem.isExpanded = !baseItem.isExpanded
                changeAdapter()
            }
        }
    }
}