package com.rekkursion.enigma.views

import android.app.AlertDialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import com.rekkursion.enigma.R
import com.rekkursion.enigma.listeners.OnButtonBarClickListener

class FolderItemCard(context: Context, attrs: AttributeSet? = null): BaseItemCard(context, attrs) {
    // override the button-bar
    override val mCancelOrSubmitButtonBar: CancelOrSubmitButtonBar

    /* =================================================================== */

    // primary constructor
    init {
        // inflate the layout
        LayoutInflater.from(context).inflate(R.layout.view_folder_item_card, this)

        // get views
        mCancelOrSubmitButtonBar = findViewById(R.id.cancel_or_submit_button_bar_at_folder_item_card)

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
}