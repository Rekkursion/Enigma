package com.rekkursion.enigma.commands

import android.view.View
import android.widget.EditText
import com.rekkursion.enigma.activities.NewItemActivity
import com.rekkursion.enigma.managers.NewItemManager
import com.rekkursion.enigma.models.FolderItem
import com.rekkursion.enigma.models.VocabularyItem
import com.rekkursion.enigma.views.itemcard.BaseItemCard
import com.rekkursion.enigma.views.itemcard.FolderItemCard

class ItemCardCreateCommand(newItemActivity: NewItemActivity): ItemCardCommand {
    override val mNewItemActivityInstance: NewItemActivity = newItemActivity

    override fun execute(vararg args: Any?) {
        // clear the new-item-list
        NewItemManager.reset()

        // add all of the new items
        args.forEach { card ->
            if (card is BaseItemCard) {
                // get content views of all fields
                val allFields = card.getAllFieldsContentViews()
                // create a new item (folder or vocabulary)
                val newItem = if (card is FolderItemCard)
                    createSingleFolderItem(allFields)
                else
                    createSingleVocabularyItem(allFields)
                // add the created new item into the new-item-list
                NewItemManager.addNewItem(newItem)
            }
        }
    }

    private fun createSingleFolderItem(allFields: ArrayList<View?>): FolderItem = FolderItem(
        arrayListOf(),
        (allFields.getOrNull(0) as? EditText)?.text.toString()
    )

    // TODO: create a single vocabulary-item
    private fun createSingleVocabularyItem(allFields: ArrayList<View?>): VocabularyItem {
        return VocabularyItem(
            arrayListOf(),
            (allFields.getOrNull(0) as? EditText)?.text.toString(),
            arrayListOf(),
            5F
        )
    }
}