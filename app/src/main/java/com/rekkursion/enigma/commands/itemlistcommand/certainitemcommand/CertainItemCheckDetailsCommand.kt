package com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.R
import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter

class CertainItemCheckDetailsCommand(recyclerView: RecyclerView): CertainItemCommand(recyclerView) {
    /**
     * @param position: Int -> the position of the recycler-view to be executed
     */
    override fun executeAt(position: Int) {
        val baseItem = (mRecvItemList.adapter as? ItemRecyclerViewAdapter)?.getBaseItemAndItsTruePosition(position)?.first
        baseItem?.let {
            AlertDialog.Builder(mRecvItemList.context)
                .setTitle(baseItem.getIdentifier())
                .setMessage(baseItem.getDetails(mRecvItemList.context))
                .setPositiveButton(mRecvItemList.context.getString(R.string.str_ok), null)
                .create()
                .show()
        }
    }
}