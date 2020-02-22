package com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand

import android.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.R
import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter

class CertainItemCheckSummaryCommand(recyclerView: RecyclerView): CertainItemCommand(recyclerView) {
    /**
     * @param position: Int -> the position of the recycler-view to be executed
     * @param args: vararg Any? -> *no parameters*
     */
    override fun executeAt(position: Int, vararg args: Any?) {
        val baseItem = (mRecvItemList.adapter as? ItemRecyclerViewAdapter)?.getBaseItemAndItsTruePosition(position)?.first
        baseItem?.let {
            AlertDialog.Builder(mRecvItemList.context)
                .setTitle(baseItem.getIdentifier())
                .setMessage(baseItem.getSummary(mRecvItemList.context))
                .setPositiveButton(mRecvItemList.context.getString(R.string.str_ok), null)
                .create()
                .show()
        }
    }
}