package com.rekkursion.enigma.states

import android.app.AlertDialog
import android.view.View
import com.rekkursion.enigma.R
import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter
import com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand.CertainItemCheckSummaryCommand
import com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand.CertainItemEnterFolderCommand
import com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand.CertainItemExpandOrUnexpandCommand
import com.rekkursion.enigma.managers.CommandManager
import com.rekkursion.enigma.viewholders.BaseItemViewHolder
import com.rekkursion.enigma.views.ListDialog

class GeneralRecvState private constructor(): RecvState {
    companion object {
        // the unique instance
        private val mInstance: GeneralRecvState = GeneralRecvState()
        // get the unique instance
        fun getInstance(): GeneralRecvState = mInstance
    }

    /* =================================================================== */

    override fun doOnClick(stateContext: RecvStateContext, position: Int) {
        when (stateContext.recyclerView.adapter?.getItemViewType(position)) {
            // folder-item
            BaseItemViewHolder.BaseItemViewType.FOLDER_ITEM.ordinal ->
                CommandManager.doCommand(CertainItemEnterFolderCommand::class, position)

            // vocabulary-item-master
            BaseItemViewHolder.BaseItemViewType.VOCABULARY_ITEM_MASTER.ordinal ->
                CommandManager.doCommand(CertainItemExpandOrUnexpandCommand::class, position)
        }
    }

    override fun doOnLongClick(stateContext: RecvStateContext, position: Int) {
        // the adapter of the recycler-view
        val adapter = stateContext.recyclerView.adapter as? ItemRecyclerViewAdapter ?: return

        // the view-type of the touched item
        val viewType = adapter.getItemViewType(position)

        // the item to be operated
        val item = adapter.getBaseItemAndItsTruePosition(position).first

        // create the list-dialog and show it
        createListDialog(stateContext, item.getIdentifier(), position, viewType).show()
    }

    override fun toString(): String = "GENERAL_STATE"

    /* =================================================================== */

    // create the list-dialog
    private fun createListDialog(stateContext: RecvStateContext, title: String, position: Int, viewType: Int): AlertDialog =
        if (viewType == BaseItemViewHolder.BaseItemViewType.FOLDER_ITEM.ordinal)
            createFolderItemListDialog(stateContext, title, position)
        else
            createVocabularyItemMasterListDialog(stateContext, title, position)

    // create the list-dialog for the folder-item
    private fun createFolderItemListDialog(stateContext: RecvStateContext, title: String, position: Int): AlertDialog {
        val context = stateContext.getContext()
        return ListDialog.Builder(context)
            // enter
            .addListItem(context.getString(R.string.str_folder_item_list_dialog_enter), View.OnClickListener {
                CommandManager.doCommand(CertainItemEnterFolderCommand::class, position)
            })
            // summary
            .addListItem(context.getString(R.string.str_folder_item_list_dialog_summary), View.OnClickListener {
                CommandManager.doCommand(CertainItemCheckSummaryCommand::class, position)
            })
            // rename
            .addListItem(context.getString(R.string.str_folder_item_list_dialog_rename), View.OnClickListener {

            })
            // move
            .addListItem(context.getString(R.string.str_folder_item_list_dialog_move), View.OnClickListener {
                stateContext.state = PickingPathRecvState.getInstance()
            })
            // delete
            .addListItem(context.getString(R.string.str_folder_item_list_dialog_delete), View.OnClickListener {

            })
            .setTitle(title)
            .create()
    }

    // create the list-dialog for the vocabulary-item-master
    private fun createVocabularyItemMasterListDialog(stateContext: RecvStateContext, title: String, position: Int): AlertDialog {
        val context = stateContext.getContext()
        return ListDialog.Builder(context)
            // details
            .addListItem(context.getString(R.string.str_vocabulary_item_master_list_dialog_details), View.OnClickListener {

            })
            // summary
            .addListItem(context.getString(R.string.str_vocabulary_item_master_list_dialog_summary), View.OnClickListener {
                CommandManager.doCommand(CertainItemCheckSummaryCommand::class, position)
            })
            // edit
            .addListItem(context.getString(R.string.str_vocabulary_item_master_list_dialog_edit), View.OnClickListener {

            })
            // move
            .addListItem(context.getString(R.string.str_vocabulary_item_master_list_dialog_move), View.OnClickListener {
                stateContext.state = PickingPathRecvState.getInstance()
            })
            // delete
            .addListItem(context.getString(R.string.str_vocabulary_item_master_list_dialog_delete), View.OnClickListener {

            })
            .setTitle(title)
            .create()
    }
}