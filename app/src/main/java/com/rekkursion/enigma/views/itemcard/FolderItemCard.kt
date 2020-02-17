package com.rekkursion.enigma.views.itemcard

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import com.rekkursion.enigma.R
import com.rekkursion.enigma.views.ItemCardField

class FolderItemCard(context: Context, attrs: AttributeSet? = null): BaseItemCard(context, attrs) {
    // the edit-text of the folder name field
    private lateinit var mEdtFolderName: EditText

    /* =================================================================== */

    // primary constructor
    init {
        // initialize fields prior to the step of set
        initFields()
        // after the initialization of all fields, set them
        setFields()
    }

    /* =================================================================== */

    // initialize all fields of a folder-item-card
    override fun initFields() {
        // the edit-text of the folder name field
        mEdtFolderName = EditText(context)
        mEdtFolderName.isSingleLine = true
    }

    // set all fields of a folder-item-card
    override fun setFields() {
        // the path-view field
        val pathViewField = ItemCardField.Builder(context)
            .setFieldName(context.getString(R.string.str_base_item_card_path_field))
            .setContentView(mPathView)
            .create()
        mLlyFieldsContainer.addView(pathViewField)

        // the edit-text of the folder name field
        val folderNameField = ItemCardField.Builder(context)
            .setFieldName(context.getString(R.string.str_folder_item_card_folder_name_field))
            .setContentView(mEdtFolderName)
            .create()
        mLlyFieldsContainer.addView(folderNameField)
    }
}