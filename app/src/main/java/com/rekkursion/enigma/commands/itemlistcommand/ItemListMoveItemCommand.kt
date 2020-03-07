package com.rekkursion.enigma.commands.itemlistcommand

import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.managers.DataManager
import com.rekkursion.enigma.managers.MovingItemManager
import com.rekkursion.enigma.managers.PathManager

class ItemListMoveItemCommand(recyclerView: RecyclerView): ItemListCommand(recyclerView) {
    override fun execute(vararg args: Any?) {
        // get the original item
        val origItem = MovingItemManager.origItem ?: return
        // move the item
        DataManager.moveItem(origItem, PathManager.getCurrentPath())
        // serialize all items
        DataManager.saveAllItemsBySerialization(mRecvItemList.context)
        // update the adapter of the recycler-view
        changeAdapter()
    }
}