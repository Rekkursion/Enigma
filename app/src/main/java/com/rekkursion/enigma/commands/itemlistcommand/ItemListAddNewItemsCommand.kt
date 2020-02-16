package com.rekkursion.enigma.commands.itemlistcommand

import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.managers.DataManager
import com.rekkursion.enigma.managers.NewItemManager

class ItemListAddNewItemsCommand(recyclerView: RecyclerView): ItemListCommand(recyclerView) {
    /**
     * @param args: *no parameters*
     */
    override fun execute(vararg args: Any?) {
        if (NewItemManager.newItemList.isNotEmpty()) {
            // add all items and notify that the data set has been changed
            DataManager.addItems(NewItemManager.newItemList)
            // serialize all items
            DataManager.saveAllItemsBySerialization(mRecvItemList.context)
            // update the adapter of the recycler-view
            changeAdapter()
        }
    }
}