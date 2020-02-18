package com.rekkursion.enigma.commands.itemcardcommand

import android.view.View
import android.widget.EditText
import com.rekkursion.enigma.activities.NewItemActivity
import com.rekkursion.enigma.managers.NewItemManager
import com.rekkursion.enigma.models.FolderItem
import com.rekkursion.enigma.models.VocabularyItem
import com.rekkursion.enigma.views.MeaningSettingFieldContentView
import com.rekkursion.enigma.views.itemcard.BaseItemCard
import com.rekkursion.enigma.views.itemcard.FolderItemCard
import com.rekkursion.exactratingbar.ExactRatingBar
import com.rekkursion.pathview.PathView
import com.rekkursion.tagview.TagCloud

class ItemCardCreateItemsCommand(newItemActivity: NewItemActivity): ItemCardCommand(newItemActivity) {
    /**
     * @param args: varargs Any? -> varargs BaseItemCard (FolderItemCard or VocabularyItemCard)
     */
    override fun execute(vararg args: Any?) {
        // clear the new-item-list
        NewItemManager.reset()

        // add all of the new items
        args.forEach { card ->
            // if it's a folder-item-card or a vocabulary-item-card (a base-item-card)
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

    // region create a single folder-item
    private fun createSingleFolderItem(allFields: ArrayList<View?>): FolderItem = FolderItem(
        (allFields.getOrNull(0) as? PathView)?.getAllPathNodes() ?: arrayListOf(),
        (allFields.getOrNull(1) as? EditText)?.text.toString()
    )
    // endregion

    // region create a single vocabulary-item
    private fun createSingleVocabularyItem(allFields: ArrayList<View?>): VocabularyItem = VocabularyItem(
        (allFields.getOrNull(0) as? PathView)?.getAllPathNodes() ?: arrayListOf(),
        (allFields.getOrNull(1) as? EditText)?.text?.toString() ?: "null",
        (allFields.getOrNull(2) as? MeaningSettingFieldContentView)?.getAllMeanings() ?: arrayListOf(),
        (allFields.getOrNull(3) as? ExactRatingBar)?.currentValue ?: 0F,
        (allFields.getOrNull(4) as? EditText)?.text?.toString() ?: "null",
        (allFields.getOrNull(5) as? TagCloud)?.getAllTagStrings()?.toCollection(ArrayList()) ?: arrayListOf()
    )
    // endregion
}