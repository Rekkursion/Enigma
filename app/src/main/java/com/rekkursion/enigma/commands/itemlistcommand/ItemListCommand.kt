package com.rekkursion.enigma.commands.itemlistcommand

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.R
import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter
import com.rekkursion.enigma.commands.BaseCommand
import com.rekkursion.enigma.enums.ItemType
import com.rekkursion.enigma.managers.DataManager
import com.rekkursion.enigma.managers.NewItemManager
import com.rekkursion.enigma.managers.PathManager

abstract class ItemListCommand(recyclerView: RecyclerView): BaseCommand {
    // the recycler-view of the item list
    protected val mRecvItemList: RecyclerView = recyclerView

    // update the adapter of the recycler-view if needs
    protected fun changeAdapter() {
        val adapter = ItemRecyclerViewAdapter(DataManager.getAllItemsAtCertainPath(PathManager.getCurrentPath()))
        mRecvItemList.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    // show the toast a string
    protected fun showToast(message: String, duringTime: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(
            mRecvItemList.context,
            message,
            duringTime
        ).show()
    }

    // show the toast with a string resource id
    protected fun showToast(stringId: Int, duringTime: Int = Toast.LENGTH_SHORT) {
        showToast(mRecvItemList.context.getString(stringId), duringTime)
    }
}