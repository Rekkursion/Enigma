package com.rekkursion.enigma.commands.itemlistcommand

import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.rekkursion.enigma.R
import com.rekkursion.enigma.enums.ItemType
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

            // show the snack-bar to let the user know the adding operation is successful
            val context = mRecvItemList.context
            val itemType = NewItemManager.newItemList[0].type
            Snackbar.make(
                mRecvItemList,
                if (itemType == ItemType.VOCABULARY)
                    context.getString(R.string.str_snack_bar_add_vocabularies_prefix) +
                            NewItemManager.newItemList.size.toString() +
                            context.getString(R.string.str_snack_bar_add_vocabularies_suffix)
                else
                    context.getString(R.string.str_snack_bar_add_vocabularies_prefix) +
                            NewItemManager.newItemList.size.toString() +
                            context.getString(R.string.str_snack_bar_add_vocabularies_suffix),
                Snackbar.LENGTH_SHORT
            ).show()
        }
    }
}