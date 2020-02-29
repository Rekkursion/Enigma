package com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand

import android.app.AlertDialog
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rekkursion.enigma.R
import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter
import com.rekkursion.enigma.managers.DataManager
import com.rekkursion.enigma.models.FolderItem

class CertainItemDeleteCommand(recyclerView: RecyclerView): CertainItemCommand(recyclerView) {
    override fun executeAt(position: Int, vararg args: Any?) {
        val baseItem = (mRecvItemList.adapter as? ItemRecyclerViewAdapter)?.getBaseItemAndItsTruePosition(position)?.first

        baseItem?.let {
            val context = mRecvItemList.context

            AlertDialog.Builder(context)
                .setTitle(
                    context.getString(R.string.str_attention_of_removing_item_dialog_title_prefix) +
                            if (baseItem is FolderItem) context.getString(R.string.str_folder) else context.getString(R.string.str_vocabulary) +
                            " \"${baseItem.getName()}\"" +
                            context.getString(R.string.str_attention_of_removing_item_dialog_title_suffix)
                )
                .setMessage(context.getString(R.string.str_attention_of_removing_item_dialog_message))
                .setPositiveButton(context.getString(R.string.str_ok)) { _, _ ->
                    // remove the item and notify that the data set has been changed
                    DataManager.removeItem(baseItem)
                    // serialize all items
                    DataManager.saveAllItemsBySerialization(mRecvItemList.context)
                    // update the adapter of the recycler-view
                    changeAdapter()

                    // show the toast to let the user know the removing operation is succeed
                    Toast.makeText(
                        mRecvItemList.context,
                        context.getString(R.string.str_toast_remove_item_prefix) +
                            "${if (baseItem is FolderItem)
                                context.getString(R.string.str_folder)
                            else
                                context.getString(R.string.str_vocabulary)} \"${baseItem.getName()}\"" +
                        context.getString(R.string.str_toast_remove_item_suffix),
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .setNegativeButton(context.getString(R.string.str_cancel), null)
                .create()
                .show()
        }
    }
}