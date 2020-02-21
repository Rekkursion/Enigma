package com.rekkursion.enigma.templates

import androidx.appcompat.app.AppCompatActivity
import com.rekkursion.enigma.R
import com.rekkursion.enigma.listeners.OnButtonBarClickListener
import com.rekkursion.enigma.views.CancelOrSubmitButtonBar
import com.rekkursion.enigma.views.itemcard.VocabularyItemCard

class InitializeEditVocabularyActivityTemplate(activity: AppCompatActivity):
    InitializeActivityTemplate(activity),
    OnButtonBarClickListener {

    // the cancel & submit button-bar
    private lateinit var mCancelSubmitButtonBar: CancelOrSubmitButtonBar

    // the item-card of the editing vocabulary
    private lateinit var mItemCard: VocabularyItemCard

    /* ================================================================== */

    override fun onCancelClickListener() {

    }

    override fun onSubmitClickListener() {

    }

    /* ================================================================== */

    override fun initViews() {
        mCancelSubmitButtonBar = mActivity.findViewById(R.id.cancel_or_submit_button_bar_at_edit_vocabulary_activity)
        mItemCard = mActivity.findViewById(R.id.item_card_at_edit_vocabulary_activity)
    }

    override fun initAttributes() {

    }

    override fun initCommands() {

    }

    override fun initEvents() {
        // click on the cancel button or the submit button
        mCancelSubmitButtonBar.setOnButtonBarClickListener(this)
    }

    override fun doAfterInitialization() {

    }
}