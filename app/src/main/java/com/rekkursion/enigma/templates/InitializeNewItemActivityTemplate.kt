package com.rekkursion.enigma.templates

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.ClipData
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.children
import com.google.android.material.snackbar.Snackbar
import com.rekkursion.enigma.R
import com.rekkursion.enigma.activities.NewItemActivity
import com.rekkursion.enigma.commands.itemcardcommand.*
import com.rekkursion.enigma.enums.ItemType
import com.rekkursion.enigma.listeners.OnButtonBarClickListener
import com.rekkursion.enigma.managers.CommandManager
import com.rekkursion.enigma.managers.NewItemManager
import com.rekkursion.enigma.views.CancelOrSubmitButtonBar

class InitializeNewItemActivityTemplate(activity: AppCompatActivity):
    InitializeActivityTemplate(activity),
    OnButtonBarClickListener {

    // the cancel & submit button-bar
    private lateinit var mCancelSubmitButtonBar: CancelOrSubmitButtonBar

    // the container for placing item-cards
    private lateinit var mLlyCardsContainer: LinearLayoutCompat

    // the button for adding a new item-cards
    private lateinit var mBtnAddNewCard: Button

    // the item-type for the new cards
    private lateinit var mItemType: ItemType

    /* ================================================================== */

    // cancel
    override fun onCancelClickListener() {
        // first, set the result of result-canceled
        mActivity.setResult(Activity.RESULT_CANCELED)
        // clear the new-item-list of new-item-manager
        NewItemManager.reset()
        // finish this activity
        mActivity.finish()
    }

    // submit
    override fun onSubmitClickListener() {
        // all of the cards are valid
        if (validateFieldsBeforeSubmission()) {
            // first, set the result of result-ok
            mActivity.setResult(Activity.RESULT_OK)
            // create all items and store them in the new-item-list of new-item-manager
            CommandManager.doCommand(ItemCardCreateItemsCommand::class, *(mLlyCardsContainer.children.toList().toTypedArray()))
            // finish this activity
            mActivity.finish()
        }
    }

    /* ================================================================== */

    override fun initViews() {
        mCancelSubmitButtonBar = mActivity.findViewById(R.id.cancel_or_submit_button_bar_at_new_item_activity)
        mLlyCardsContainer = mActivity.findViewById(R.id.lly_new_item_cards_container_at_new_item_activity)
        mBtnAddNewCard = mActivity.findViewById(R.id.btn_add_new_card_at_new_item_activity)
    }

    @SuppressLint("SetTextI18n")
    override fun initAttributes() {
        mBtnAddNewCard.text = mActivity.getString(R.string.str_add_more) + "（1）"
        mItemType = if (mActivity.intent.getStringExtra(ItemType::name.toString()) == ItemType.FOLDER.name)
            ItemType.FOLDER
        else
            ItemType.VOCABULARY
    }

    override fun initCommands() {
        // command of adding a new item-card
        CommandManager.putCommand(ItemCardAddCommand::class, ItemCardAddCommand(mActivity, mLlyCardsContainer))
        // command of removing a certain item-card
        CommandManager.putCommand(ItemCardRemoveCommand::class, ItemCardRemoveCommand(mActivity, mLlyCardsContainer))
        // command of going-up or going-down of a certain item-card
        CommandManager.putCommand(ItemCardGoUpOrGoDownCommand::class, ItemCardGoUpOrGoDownCommand(mActivity, mLlyCardsContainer))
        // command of creating all items
        CommandManager.putCommand(ItemCardCreateItemsCommand::class, ItemCardCreateItemsCommand(mActivity, mLlyCardsContainer))
        // command of validating if cards are all valid or not before the submission
        CommandManager.putCommand(ItemCardValidateCommand::class, ItemCardValidateCommand(mActivity, mLlyCardsContainer))
    }

    @SuppressLint("SetTextI18n")
    override fun initEvents() {
        // click on the add-new-card button
        mBtnAddNewCard.setOnClickListener {
            if (mLlyCardsContainer.childCount == NewItemActivity.MAX_NUMBER_OF_NEW_ITEMS)
                Snackbar.make(
                    mBtnAddNewCard,
                    mActivity.getString(R.string.str_attention_of_maximum_new_items_prefix) + NewItemActivity.MAX_NUMBER_OF_NEW_ITEMS.toString() + mActivity.getString(R.string.str_attention_of_maximum_new_items_suffix),
                    Snackbar.LENGTH_SHORT
                ).show()
            else {
                // add a new card
                addNewCard(mItemType)
                // change the text of this button
                mBtnAddNewCard.text = mActivity.getString(R.string.str_add_more) + "（${mLlyCardsContainer.childCount}）"
            }
        }

        // click on the cancel button or the submit button
        mCancelSubmitButtonBar.setOnButtonBarClickListener(this)
    }

    override fun doAfterInitialization() {
        addNewCard(mItemType)
    }

    /* ================================================================== */

    // add a new item card
    private fun addNewCard(itemType: ItemType) {
        CommandManager.doCommand(ItemCardAddCommand::class, itemType, mCancelSubmitButtonBar, mBtnAddNewCard)
    }

    // check if all fields are valid or not before the submission
    private fun validateFieldsBeforeSubmission(): Boolean {
        CommandManager.doCommand(ItemCardValidateCommand::class, mItemType)
        val valid = ItemCardValidateCommand.validateResult == ItemCardValidateCommand.ValidationResult.VALID
        if (!valid)
            AlertDialog.Builder(mActivity)
                .setMessage(ItemCardValidateCommand.validateResult.warningStringId)
                .setPositiveButton(mActivity.getString(R.string.str_ok), null)
                .create()
                .show()
        return valid
    }
}