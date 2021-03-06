package com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand

import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rekkursion.enigma.R
import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter
import com.rekkursion.enigma.managers.DataManager
import com.rekkursion.enigma.models.BaseItem
import com.rekkursion.enigma.models.VocabularyItem

class CertainItemAlterVocabularyCommand(recyclerView: RecyclerView): CertainItemCommand(recyclerView) {
    /**
     * @param args: *no parameters*
     */
    override fun executeAt(position: Int, vararg args: Any?) {
        val editedItem = args[0] as BaseItem
        val itemInList = ((mRecvItemList.adapter as? ItemRecyclerViewAdapter)?.getBaseItemAndItsTruePosition(position)?.first ?: return) as? VocabularyItem ?: return

        // alter this item stored in the list
        itemInList.alterFrom(editedItem)
        // serialize all items
        DataManager.saveAllItemsBySerialization(mRecvItemList.context)
        // update the adapter of the recycler-view
        changeAdapter()

        // show the toast to let the user know the editing operation is succeed
        showToast(
            mRecvItemList.context.getString(R.string.str_toast_edit_vocabulary_prefix) +
                    itemInList.getName() +
                    mRecvItemList.context.getString(R.string.str_toast_edit_vocabulary_suffix)
        )
    }
}