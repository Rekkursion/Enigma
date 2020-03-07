package com.rekkursion.enigma.commands.itemlistcommand.itemlistshowdialogcommand

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.R
import com.rekkursion.enigma.activities.EditVocabularyActivity
import com.rekkursion.enigma.activities.VocabularyActivity
import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter
import com.rekkursion.enigma.enums.CommandType
import com.rekkursion.enigma.managers.CommandManager
import com.rekkursion.enigma.managers.MovingItemManager
import com.rekkursion.enigma.managers.PathManager
import com.rekkursion.enigma.models.BaseItem
import com.rekkursion.enigma.models.VocabularyItem
import com.rekkursion.enigma.states.PickingPathRecvState
import com.rekkursion.enigma.states.RecvStateContext
import com.rekkursion.enigma.views.ListDialog
import kotlin.reflect.KClass

class ItemListShowDialogVocabularyItemMasterCommand(recyclerView: RecyclerView): ItemListShowDialogCommand(recyclerView) {
    @Suppress("UNCHECKED_CAST")
    override fun createListDialog(stateContext: RecvStateContext, dialogTitle: String, position: Int): AlertDialog? {
        val context = stateContext.getContext()
        val item = (mRecvItemList.adapter as? ItemRecyclerViewAdapter)?.getBaseItemAndItsTruePosition(position)?.first as? VocabularyItem
            ?: return null

        return ListDialog.Builder(context)
            // details
            .addListItem(context.getString(R.string.str_vocabulary_item_master_list_dialog_details), View.OnClickListener {
                goToCertainActivity(context, VocabularyActivity::class as KClass<AppCompatActivity>, item, position)
            })

            // summary
            .addListItem(context.getString(R.string.str_vocabulary_item_master_list_dialog_summary), View.OnClickListener {
                // show the summary dialog for this vocabulary-item
                CommandManager.doCommand(CommandType.CERTAIN_ITEM_CHECK_SUMMARY, position)
            })

            // edit
            .addListItem(context.getString(R.string.str_vocabulary_item_master_list_dialog_edit), View.OnClickListener {
                goToCertainActivity(context, EditVocabularyActivity::class as KClass<AppCompatActivity>, item, position)
            })

            // move
            .addListItem(context.getString(R.string.str_vocabulary_item_master_list_dialog_move), View.OnClickListener {
                CommandManager.doCommand(CommandType.ITEM_LIST_EXPAND_OR_UNEXPAND_ALL_VOCABULARIES, false)
                MovingItemManager.startMoving(item, PathManager.getCurrentPath(), position)
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

    // go to a certain activity with the base-item and its position in the recycler-view
    private fun goToCertainActivity(context: Context, activityClass: KClass<AppCompatActivity>, item: BaseItem?, position: Int) {
        // create an intent to go to the designated activity
        val theIntent = Intent(context, activityClass.java)

        // put this vocabulary-item into a bundle
        val bundle = Bundle()
        bundle.putSerializable(VocabularyItem::class.java.name, item)
        bundle.putInt("position", position)

        // put the bundle as extras
        theIntent.putExtras(bundle)

        // start the activity
        context.startActivity(theIntent)
    }
}