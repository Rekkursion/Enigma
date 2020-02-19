package com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand

import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter
import com.rekkursion.enigma.models.VocabularyItem

class CertainItemExpandOrUnexpandCommand(recyclerView: RecyclerView): CertainItemCommand(recyclerView) {
    /**
     * @param position: Int -> the position of the recycler-view to be executed
     */
    override fun executeAt(position: Int) {
        val baseItem = (mRecvItemList.adapter as? ItemRecyclerViewAdapter)?.getBaseItemAndItsTruePosition(position)?.first
        baseItem?.let {
            if (baseItem is VocabularyItem) {
                baseItem.isExpanded = !baseItem.isExpanded
                mRecvItemList.adapter?.notifyDataSetChanged()
            }
        }
    }
}