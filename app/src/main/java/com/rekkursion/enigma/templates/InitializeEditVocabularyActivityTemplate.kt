package com.rekkursion.enigma.templates

import android.app.AlertDialog
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.rekkursion.enigma.R
import com.rekkursion.enigma.commands.itemcardcommand.ItemCardValidateCommand
import com.rekkursion.enigma.enums.CommandType
import com.rekkursion.enigma.listeners.OnButtonBarClickListener
import com.rekkursion.enigma.managers.CommandManager
import com.rekkursion.enigma.managers.DataManager
import com.rekkursion.enigma.models.VocabularyItem
import com.rekkursion.enigma.views.CancelOrSubmitButtonBar
import com.rekkursion.enigma.views.itemcard.VocabularyItemCard

class InitializeEditVocabularyActivityTemplate(activity: AppCompatActivity):
    InitializeActivityTemplate(activity),
    OnButtonBarClickListener {

    // the cancel & submit button-bar
    private lateinit var mCancelSubmitButtonBar: CancelOrSubmitButtonBar

    // the item to be altered
    private lateinit var mItem: VocabularyItem

    // the item-card of the editing vocabulary
    private lateinit var mItemCard: VocabularyItemCard

    // the original english (for checking if the english field has been altered or not)
    private lateinit var mOriginalEnglish: String

    // the position of the selected child-view in the recycler-view
    private var mPositionInRecv: Int = 0

    /* ================================================================== */

    override fun onCancelClickListener() {
        // finish this activity
        mActivity.finish()
    }

    override fun onSubmitClickListener() {
        // valid
        if (validateEnglishDuplicatedBeforeSubmission()) {
            // do the command of altering the item in the recycler-view
            CommandManager.doCommand(CommandType.CERTAIN_ITEM_ALTER_VOCABULARY, mPositionInRecv, mItemCard.createItemModel())
            // finish this activity
            mActivity.finish()
        }
        // invalid (the same english-word has already existed)
        else AlertDialog.Builder(mActivity).setMessage(
            R.string.str_attention_of_invalid_same_vocabulary
        ).create().show()
    }

    /* ================================================================== */

    override fun initViews() {
        mCancelSubmitButtonBar = mActivity.findViewById(R.id.cancel_or_submit_button_bar_at_edit_vocabulary_activity)
        mItemCard = mActivity.findViewById(R.id.item_card_at_edit_vocabulary_activity)
    }

    override fun initAttributes() {
        // get the vocabulary-item
        val item = mActivity.intent.extras
            ?.getSerializable(VocabularyItem::class.java.name) as? VocabularyItem

        // get the position of the selected child in the recycler-view
        val pos = mActivity.intent.extras?.getInt("position")

        // if failed, finish the activity directly
        if (item == null || pos == null) mActivity.finish()

        // set data of all content views by this item
        mItemCard.setDataOfContentViewsByItemModel(item)
        // hide the actions-bar of this card since it's redundant when editing a single vocabulary
        mItemCard.hideActionsBar()

        // set the item
        mItem = item!!

        // set the position of the selected child in the recycler-view
        mPositionInRecv = pos!!

        // set the original english
        mOriginalEnglish = mItem.english
    }

    override fun initCommands() {

    }

    override fun initEvents() {
        // click on the cancel button or the submit button
        mCancelSubmitButtonBar.setOnButtonBarClickListener(this)
    }

    override fun doAfterInitialization() {

    }

    /* ================================================================== */

    // check if the altered english is duplicated or not before the submission
    private fun validateEnglishDuplicatedBeforeSubmission(): Boolean {
        val item = mItemCard.createItemModel() as? VocabularyItem ?: return false
        val hasEnglishBeenAltered = item.english != mOriginalEnglish
        return hasEnglishBeenAltered.not() || DataManager.containsVocabulary(item.english).not()
    }
}