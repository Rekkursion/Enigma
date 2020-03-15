package com.rekkursion.enigma.commands.itemlistcommand

import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.R
import com.rekkursion.enigma.managers.DataManager
import com.rekkursion.enigma.managers.MovingItemManager
import com.rekkursion.enigma.managers.PathManager

class ItemListMoveItemCommand(recyclerView: RecyclerView): ItemListCommand(recyclerView) {
    override fun execute(vararg args: Any?) {
        // get the original item
        val origItem = MovingItemManager.origItem ?: return

        // get the string id for toast which differs from the fail or success of the moving operation
        val toastMsgId = when {
            // there's no change of the path
            origItem.pathString == PathManager.getCurrentPath() ->
                R.string.str_toast_move_item_failed_due_to_no_change_on_path

            PathManager.getCurrentPath().startsWith(origItem.pathString) ->
                R.string.str_toast_move_item_failed_due_to_recursion

            // there's already a folder with the same name at the current path
            DataManager.containsFolderAtCertainPath(origItem.getName()) ->
                R.string.str_toast_move_item_failed_due_to_same_folder_name_exists

            // no problem, the item can be moved
            else -> {
                // move the item
                DataManager.moveItem(origItem, PathManager.getCurrentPath())
                // serialize all items
                DataManager.saveAllItemsBySerialization(mRecvItemList.context)
                // update the adapter of the recycler-view
                changeAdapter()

                R.string.str_toast_move_item_successfully
            }
        }

        // show the toast to tell the user the moving operation has been succeed
        showToast(toastMsgId)
    }
}