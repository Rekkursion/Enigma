package com.rekkursion.enigma.commands.itemlistcommand

import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.managers.DataManager

class ItemListLoadAllItemsCommand(recyclerView: RecyclerView): ItemListCommand(recyclerView) {
    /**
     * @param args: *no parameters*
     */
    override fun execute(vararg args: Any?) {
        // load all saved items by de-serialization
        DataManager.loadAllItemsByDeSerialization(mRecvItemList.context, false)
        // update the adapter of the recycler-view
        changeAdapter()
    }
}