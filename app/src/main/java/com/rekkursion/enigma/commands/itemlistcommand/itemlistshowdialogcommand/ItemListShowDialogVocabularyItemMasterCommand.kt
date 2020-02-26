package com.rekkursion.enigma.commands.itemlistcommand.itemlistshowdialogcommand

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.R
import com.rekkursion.enigma.activities.EditVocabularyActivity
import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter
import com.rekkursion.enigma.enums.CommandType
import com.rekkursion.enigma.managers.CommandManager
import com.rekkursion.enigma.models.VocabularyItem
import com.rekkursion.enigma.states.PickingPathRecvState
import com.rekkursion.enigma.states.RecvStateContext
import com.rekkursion.enigma.views.ListDialog

class ItemListShowDialogVocabularyItemMasterCommand(recyclerView: RecyclerView): ItemListShowDialogCommand(recyclerView) {
    override fun createListDialog(stateContext: RecvStateContext, dialogTitle: String, position: Int): AlertDialog {
        val context = stateContext.getContext()
        val item = (mRecvItemList.adapter as? ItemRecyclerViewAdapter)?.getBaseItemAndItsTruePosition(position)?.first as? VocabularyItem

        return ListDialog.Builder(context)
            // details
            .addListItem(context.getString(R.string.str_vocabulary_item_master_list_dialog_details), View.OnClickListener {

            })

            // summary
            .addListItem(context.getString(R.string.str_vocabulary_item_master_list_dialog_summary), View.OnClickListener {
                // show the summary dialog for this vocabulary-item
                CommandManager.doCommand(CommandType.CERTAIN_ITEM_CHECK_SUMMARY, position)
            })

            // edit
            .addListItem(context.getString(R.string.str_vocabulary_item_master_list_dialog_edit), View.OnClickListener {
                // create an intent to go to edit-vocabulary-activity
                val intentToEditVocabularyActivity = Intent(context, EditVocabularyActivity::class.java)

                // put this vocabulary-item into a bundle
                val bundle = Bundle()
                bundle.putSerializable(VocabularyItem::class.java.name, item)
                bundle.putInt("position", position)

                // put the bundle as extras
                intentToEditVocabularyActivity.putExtras(bundle)

                // start the activity to go to edit-vocabulary-activity
                context.startActivity(intentToEditVocabularyActivity)
            })

            // move
            .addListItem(context.getString(R.string.str_vocabulary_item_master_list_dialog_move), View.OnClickListener {
                stateContext.state = PickingPathRecvState.getInstance()
            })

            // delete
            .addListItem(context.getString(R.string.str_vocabulary_item_master_list_dialog_delete), View.OnClickListener {
                // delete this vocabulary-item
                CommandManager.doCommand(CommandType.CERTAIN_ITEM_DELETE, position)
            })

            .setTitle(dialogTitle)
            .create()
    }
}