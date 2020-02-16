package com.rekkursion.enigma.commands.itemlistcommand

import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter
import com.rekkursion.enigma.commands.BaseCommand
import com.rekkursion.enigma.managers.PathManager

abstract class ItemListCommand(recyclerView: RecyclerView): BaseCommand {
    // the recycler-view of the item list
    protected val mRecvItemList: RecyclerView = recyclerView

    // update the adapter of the recycler-view if needs
    protected fun changeAdapter() {
        val adapter = ItemRecyclerViewAdapter(PathManager.itemListForRecv)
        mRecvItemList.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}