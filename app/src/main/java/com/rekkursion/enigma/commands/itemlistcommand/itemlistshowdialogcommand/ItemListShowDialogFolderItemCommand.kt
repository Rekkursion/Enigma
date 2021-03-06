package com.rekkursion.enigma.commands.itemlistcommand.itemlistshowdialogcommand

import android.app.AlertDialog
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.R
import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter
import com.rekkursion.enigma.enums.CommandType
import com.rekkursion.enigma.managers.CommandManager
import com.rekkursion.enigma.managers.MovingItemManager
import com.rekkursion.enigma.managers.PathManager
import com.rekkursion.enigma.models.FolderItem
import com.rekkursion.enigma.models.VocabularyItem
import com.rekkursion.enigma.states.PickingPathRecvState
import com.rekkursion.enigma.states.RecvStateContext
import com.rekkursion.enigma.views.ListDialog

class ItemListShowDialogFolderItemCommand(recyclerView: RecyclerView): ItemListShowDialogCommand(recyclerView) {
    override fun createListDialog(stateContext: RecvStateContext, dialogTitle: String, position: Int): AlertDialog? {
        val context = stateContext.getContext()
        val item = (mRecvItemList.adapter as? ItemRecyclerViewAdapter)?.getBaseItemAndItsTruePosition(position)?.first as? FolderItem
            ?: return null

        return ListDialog.Builder(context)
            // enter
            .addListItem(context.getString(R.string.str_folder_item_list_dialog_enter), View.OnClickListener {
                // go into this folder-item's content
                CommandManager.doCommand(CommandType.CERTAIN_ITEM_ENTER_FOLDER, position)
            })

            // summary
            .addListItem(context.getString(R.string.str_folder_item_list_dialog_summary), View.OnClickListener {
                // show the summary dialog for this folder-item
                CommandManager.doCommand(CommandType.CERTAIN_ITEM_CHECK_SUMMARY, position)
            })

            // rename
            .addListItem(context.getString(R.string.str_folder_item_list_dialog_rename), View.OnClickListener {
                // rename this folder-item
                CommandManager.doCommand(CommandType.CERTAIN_ITEM_RENAME_FOLDER, position)
            })

            // move
            .addListItem(context.getString(R.string.str_folder_item_list_dialog_move), View.OnClickListener {
                CommandManager.doCommand(CommandType.ITEM_LIST_EXPAND_OR_UNEXPAND_ALL_VOCABULARIES, false)
                MovingItemManager.startMoving(item, PathManager.getCurrentPath(), position)
                stateContext.state = PickingPathRecvState.getInstance()
            })

            // delete
            .addListItem(context.getString(R.string.str_folder_item_list_dialog_delete), View.OnClickListener {
                // delete this folder-item
                CommandManager.doCommand(CommandType.CERTAIN_ITEM_DELETE, position)
            })

            .setTitle(dialogTitle)
            .create()
    }
}