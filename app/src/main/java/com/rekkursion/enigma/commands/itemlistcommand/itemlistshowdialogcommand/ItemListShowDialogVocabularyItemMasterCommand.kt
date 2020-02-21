package com.rekkursion.enigma.commands.itemlistcommand.itemlistshowdialogcommand

import android.app.AlertDialog
import android.content.Intent
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.R
import com.rekkursion.enigma.activities.EditVocabularyActivity
import com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand.CertainItemCheckSummaryCommand
import com.rekkursion.enigma.managers.CommandManager
import com.rekkursion.enigma.states.PickingPathRecvState
import com.rekkursion.enigma.states.RecvStateContext
import com.rekkursion.enigma.views.ListDialog

class ItemListShowDialogVocabularyItemMasterCommand(recyclerView: RecyclerView): ItemListShowDialogCommand(recyclerView) {
    override fun createListDialog(stateContext: RecvStateContext, dialogTitle: String, position: Int): AlertDialog {
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
                val intentToEditVocabularyActivity = Intent(context, EditVocabularyActivity::class.java)
                context.startActivity(intentToEditVocabularyActivity)
            })
            // move
            .addListItem(context.getString(R.string.str_vocabulary_item_master_list_dialog_move), View.OnClickListener {
                stateContext.state = PickingPathRecvState.getInstance()
            })
            // delete
            .addListItem(context.getString(R.string.str_vocabulary_item_master_list_dialog_delete), View.OnClickListener {

            })
            .setTitle(dialogTitle)
            .create()
    }
}