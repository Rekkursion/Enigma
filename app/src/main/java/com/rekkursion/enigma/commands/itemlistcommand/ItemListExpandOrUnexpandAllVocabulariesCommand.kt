package com.rekkursion.enigma.commands.itemlistcommand

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter
import com.rekkursion.enigma.models.VocabularyItem

class ItemListExpandOrUnexpandAllVocabulariesCommand(recyclerView: RecyclerView): ItemListCommand(recyclerView) {
    /**
     * @param args: *no parameters*
     */
    override fun execute(vararg args: Any?) {
        val shouldExpand = args[0] as Boolean

        val adapter = mRecvItemList.adapter as? ItemRecyclerViewAdapter
        adapter?.let {
            for (k in 0 until adapter.itemCount) {
                val item = adapter.getBaseItemAndItsTruePosition(k).first
                if (item is VocabularyItem && shouldExpand.xor(item.isExpanded)) {
                    item.isExpanded = !item.isExpanded
                }
            }
        }

        // update the adapter of the recycler-view
        changeAdapter()
    }
}