package com.rekkursion.enigma.views.itemcard

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import com.rekkursion.enigma.R
import com.rekkursion.enigma.views.ItemCardField

class FolderItemCard(context: Context, attrs: AttributeSet? = null): BaseItemCard(context, attrs) {
    // primary constructor
    init {
        // set fields
        setFields()
    }

    /* =================================================================== */

    // initialize all fields of a folder-item-card
    override fun initFields() {

    }

    // set all fields of a folder-item-card
    override fun setFields() {
        val folderNameField = ItemCardField.Builder(context)
            .setFieldName(context.getString(R.string.str_folder_item_card_folder_name_field))
            .setContentView(EditText(context))
            .create()
        mLlyFieldsContainer.addView(folderNameField)
    }
}