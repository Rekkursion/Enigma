package com.rekkursion.enigma.commands.itemcardcommand

import android.content.Context
import android.widget.EditText
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.children
import com.rekkursion.enigma.R
import com.rekkursion.enigma.enums.ItemType
import com.rekkursion.enigma.managers.DataManager
import com.rekkursion.enigma.views.itemcard.FolderItemCard
import com.rekkursion.enigma.views.itemcard.VocabularyItemCard

class ItemCardValidateCommand(context: Context, cardContainer: LinearLayoutCompat): ItemCardCommand(context, cardContainer) {
    // the possible results of an item-card validation
    enum class ValidationResult(val warningStringId: Int) {
        VALID(0),
        INVALID_NULL_INPUT(R.string.str_attention_of_invalid_null_input),
        INVALID_SAME_FOLDER_NAME_AT_SAME_LOCATION(R.string.str_attention_of_invalid_same_folder_name_at_same_location),
        INVALID_SAME_VOCABULARY(R.string.str_attention_of_invalid_same_vocabulary),
        INVALID_DUPLICATED_NEW_FOLDERS(R.string.str_attention_of_invalid_duplicated_new_folders),
        INVALID_DUPLICATED_NEW_VOCABULARIES(R.string.str_attention_of_invalid_duplicated_new_vocabularies)
    }

    /* =================================================================== */

    companion object {
        var validateResult = ValidationResult.VALID
    }

    /* =================================================================== */

    /**
     * @param args: varargs Any? {
     *      1. ItemType: the type of the item-card to be validated (folder-item-card or vocabulary-item-card)
     * }
     */
    override fun execute(vararg args: Any?) {
        // the item-type to be validated
        val itemType = args[0] as ItemType

        // for detecting duplicated items
        val mNameHashSet = HashSet<String>()

        // cards are the folder-item-cards
        if (itemType == ItemType.FOLDER) {
            mCardContainer.children.forEach { card ->
                val allFields = (card as FolderItemCard).getAllFieldsContentViews()
                val folderName = (allFields.getOrNull(1) as? EditText)?.text?.toString()

                // duplicated folders
                if (mNameHashSet.contains(folderName)) {
                    validateResult = ValidationResult.INVALID_DUPLICATED_NEW_FOLDERS
                    return@execute
                }
                // null input
                if (folderName.isNullOrEmpty()) {
                    validateResult = ValidationResult.INVALID_NULL_INPUT
                    return@execute
                }
                // this folder already exists at the current path
                if (DataManager.containsFolderAtCertainPath(folderName)) {
                    validateResult = ValidationResult.INVALID_SAME_FOLDER_NAME_AT_SAME_LOCATION
                    return@execute
                }

                mNameHashSet.add(folderName)
            }
        }
        // cards are the vocabulary-item-cards
        else {
            mCardContainer.children.forEach { card ->
                val allFields = (card as VocabularyItemCard).getAllFieldsContentViews()
                val english = (allFields.getOrNull(1) as? EditText)?.text?.toString()

                // duplicated vocabularies
                if (mNameHashSet.contains(english)) {
                    validateResult = ValidationResult.INVALID_DUPLICATED_NEW_VOCABULARIES
                    return@execute
                }
                // null input
                if (english.isNullOrEmpty()) {
                    validateResult = ValidationResult.INVALID_NULL_INPUT
                    return@execute
                }
                // this vocabulary already exists at a global range
                if (DataManager.containsVocabulary(english)) {
                    validateResult = ValidationResult.INVALID_SAME_VOCABULARY
                    return@execute
                }

                mNameHashSet.add(english)
            }
        }

        validateResult = ValidationResult.VALID
    }
}