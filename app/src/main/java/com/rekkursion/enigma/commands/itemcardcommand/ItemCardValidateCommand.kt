package com.rekkursion.enigma.commands.itemcardcommand

import android.widget.EditText
import androidx.core.view.children
import com.rekkursion.enigma.R
import com.rekkursion.enigma.activities.NewItemActivity
import com.rekkursion.enigma.enums.ItemType
import com.rekkursion.enigma.managers.DataManager
import com.rekkursion.enigma.views.itemcard.FolderItemCard
import com.rekkursion.enigma.views.itemcard.VocabularyItemCard

class ItemCardValidateCommand(newItemActivity: NewItemActivity): ItemCardCommand(newItemActivity) {
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

    override fun execute(vararg args: Any?) {
        // for detecting duplicated items
        val mNameHashSet = HashSet<String>()

        // cards are the folder-item-cards
        if (mNewItemActivityInstance.intent.getStringExtra(ItemType::name.toString()) == ItemType.FOLDER.name) {
            mNewItemActivityInstance.mLlyCardsContainer.children.forEach { card ->
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
            mNewItemActivityInstance.mLlyCardsContainer.children.forEach { card ->
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