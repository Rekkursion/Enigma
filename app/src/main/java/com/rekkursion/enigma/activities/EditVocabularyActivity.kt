package com.rekkursion.enigma.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rekkursion.enigma.R
import com.rekkursion.enigma.views.CancelOrSubmitButtonBar
import com.rekkursion.enigma.views.itemcard.VocabularyItemCard

class EditVocabularyActivity: AppCompatActivity() {
    // the cancel & submit button-bar
    private lateinit var mCancelSubmitButtonBar: CancelOrSubmitButtonBar

    // the item-card of the editing vocabulary
    private lateinit var mItemCard: VocabularyItemCard

    /* ================================================================== */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_vocabulary)

        initViews()
        initCommands()
        initEvents()
    }

    private fun initViews() {
        mCancelSubmitButtonBar = findViewById(R.id.cancel_or_submit_button_bar_at_edit_vocabulary_activity)
        mItemCard = findViewById(R.id.item_card_at_edit_vocabulary_activity)
    }

    private fun initCommands() {

    }

    private fun initEvents() {

    }
}
