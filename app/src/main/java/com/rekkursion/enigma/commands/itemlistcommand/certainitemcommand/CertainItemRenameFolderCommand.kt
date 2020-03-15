package com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand

import android.app.AlertDialog
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.R
import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter
import com.rekkursion.enigma.managers.DataManager
import com.rekkursion.enigma.models.FolderItem

class CertainItemRenameFolderCommand(recyclerView: RecyclerView): CertainItemCommand(recyclerView) {
    override fun executeAt(position: Int, vararg args: Any?) {
        val baseItem = (mRecvItemList.adapter as? ItemRecyclerViewAdapter)?.getBaseItemAndItsTruePosition(position)?.first

        if (baseItem != null && baseItem is FolderItem) {
            // get the context
            val context = mRecvItemList.context

            // create the edit-text for inputting the new folder name at the dialog
            val edtNewFolderName = EditText(context)
            edtNewFolderName.hint = context.getString(R.string.str_attention_of_renaming_folder_item_dialog_edit_text_hint)
            edtNewFolderName.requestFocus()

            // create the dialog
            val dialog = AlertDialog.Builder(context)
                .setTitle(
                    context.getString(R.string.str_attention_of_renaming_folder_item_dialog_title) + baseItem.folderName
                )
                .setView(edtNewFolderName)
                .setPositiveButton(context.getString(R.string.str_ok), null)
                .setNegativeButton(context.getString(R.string.str_cancel), null)
                .create()

            // set the listener for the positive button at the dialog
            dialog.setOnShowListener {
                (dialog as AlertDialog).getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                    // get the new folder name from the edit-text
                    val newFolderName = edtNewFolderName.text.toString()

                    // check if the new folder name is valid or not
                    if (validateNewFolderName(context, baseItem, newFolderName)) {
                        // rename the folder-item and notify that the data set has been changed
                        DataManager.renameFolderItem(baseItem, newFolderName)
                        // serialize all items
                        DataManager.saveAllItemsBySerialization(mRecvItemList.context)
                        // update the adapter of the recycler-view
                        changeAdapter()

                        // show the toast to let the user know the folder-renaming operation is succeed
                        showToast(R.string.str_toast_rename_folder_item)

                        // dismiss the dialog
                        dialog.dismiss()
                    }
                }
            }

            // show the dialog
            dialog.show()
        }
    }

    // check if a certain new folder name is valid or not
    private fun validateNewFolderName(context: Context, origFolder: FolderItem, newFolderName: String): Boolean {
        val warningStringId: Int = when {
            newFolderName.isEmpty() -> R.string.str_attention_of_invalid_null_input
            newFolderName == origFolder.folderName -> R.string.str_attention_of_renamed_folder_no_change
            DataManager.containsFolderAtCertainPath(newFolderName) -> R.string.str_attention_of_invalid_same_folder_name_at_same_location
            else -> return true
        }

        AlertDialog.Builder(context).setMessage(warningStringId).create().show()
        return false
    }
}