package com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rekkursion.enigma.R
import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter
import com.rekkursion.enigma.managers.DataManager
import com.rekkursion.enigma.models.BaseItem

class CertainItemAlterVocabularyCommand(recyclerView: RecyclerView): CertainItemCommand(recyclerView) {
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

        // show the snack-bar to let the user know the editing operation is successful
        val context = mRecvItemList.context
        Snackbar.make(
            mRecvItemList,
            context.getString(R.string.str_snack_bar_edit_vocabulary_prefix) +
                    itemInList.getIdentifier() +
                    context.getString(R.string.str_snack_bar_edit_vocabulary_suffix),
            Snackbar.LENGTH_SHORT
        ).show()
    }
}