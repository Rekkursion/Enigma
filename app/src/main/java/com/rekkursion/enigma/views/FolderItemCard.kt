package com.rekkursion.enigma.views

import android.app.AlertDialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.widget.LinearLayoutCompat
import com.rekkursion.enigma.R
import com.rekkursion.enigma.listeners.OnButtonBarClickListener

class FolderItemCard(context: Context, attrs: AttributeSet? = null): BaseItemCard(context, attrs) {
    // override the linear-layout for placing all fields
    override val mLlyFieldsContainer: LinearLayoutCompat

    // override the button-bar w/ cancel & submit buttons
    override val mCancelOrSubmitButtonBar: CancelOrSubmitButtonBar

    /* =================================================================== */

    // the field of the folder name
    private val mIcfFolderName: ItemCardField

    /* =================================================================== */

    // primary constructor
    init {
        // inflate the layout
        LayoutInflater.from(context).inflate(R.layout.view_folder_item_card, this)

        // get views
        mLlyFieldsContainer = findViewById(R.id.lly_fields_container_at_folder_item_card)
        mCancelOrSubmitButtonBar = findViewById(R.id.cancel_or_submit_button_bar_at_folder_item_card)
        mIcfFolderName = findViewById(R.id.icf_folder_name_at_folder_item_card)

        // set fields
        setFields()

        // set events
        mCancelOrSubmitButtonBar.setOnButtonBarClickListener(object: OnButtonBarClickListener {
            override fun onCancelClickListener() {
                AlertDialog.Builder(context).setMessage("cancel").show()
            }

            override fun onSubmitClickListener() {
                AlertDialog.Builder(context).setMessage("submit").show()
            }
        })
    }

    /* =================================================================== */

    // set all fields of a folder-item-card
    private fun setFields() {
        mIcfFolderName.setFieldName(context.getString(R.string.str_folder_item_card_folder_name_field))
        mIcfFolderName.addContentView(EditText(context))
    }
}