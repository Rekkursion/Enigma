package com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand

import androidx.core.view.get
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter
import com.rekkursion.enigma.managers.DataManager
import com.rekkursion.enigma.models.BaseItem

class CertainItemEditVocabularyCommand(recyclerView: RecyclerView): CertainItemCommand(recyclerView) {
    /**
     * @param args: *no parameters*
     */
    override fun executeAt(position: Int, vararg args: Any?) {
        val editedItem = args[0] as BaseItem
        val itemInList = (mRecvItemList.adapter as? ItemRecyclerViewAdapter)?.getBaseItemAndItsTruePosition(position)?.first ?: return

        // alter this item stored in the list
        itemInList.alterFrom(editedItem)
        // serialize all items
        DataManager.saveAllItemsBySerialization(mRecvItemList.context)
        // update the adapter of the recycler-view
        changeAdapter()
    }
}