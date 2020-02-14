package com.rekkursion.enigma.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.children
import com.google.android.material.snackbar.Snackbar
import com.rekkursion.enigma.R
import com.rekkursion.enigma.enums.ItemType
import com.rekkursion.enigma.listeners.OnActionsClickListener
import com.rekkursion.enigma.listeners.OnButtonBarClickListener
import com.rekkursion.enigma.managers.NewItemFieldsManager
import com.rekkursion.enigma.views.itemcard.BaseItemCard
import com.rekkursion.enigma.views.CancelOrSubmitButtonBar
import com.rekkursion.enigma.views.itemcard.FolderItemCard
import com.rekkursion.enigma.views.itemcard.VocabularyItemCard

@SuppressLint("SetTextI18n")
class NewItemActivity: AppCompatActivity() {
    companion object {
        private const val MAX_NUMBER_OF_NEW_ITEMS = 99
    }

    /* ================================================================== */

    // the cancel & submit button-bar
    private lateinit var mCancelSubmitButtonBar: CancelOrSubmitButtonBar

    // the container for placing item-cards
    private lateinit var mLlyCardsContainer: LinearLayoutCompat

    // the button for adding a new item-cards
    private lateinit var mBtnAddNewCard: Button

    /* ================================================================== */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_item)

        initViews()
        initEvents()

        addNewCard()
    }

    /* ================================================================== */

    // initialize views
    private fun initViews() {
        mCancelSubmitButtonBar = findViewById(R.id.cancel_or_submit_button_bar_at_new_item_activity)
        mLlyCardsContainer = findViewById(R.id.lly_new_item_cards_container_at_new_item_activity)
        mBtnAddNewCard = findViewById(R.id.btn_add_new_card_at_new_item_activity)

        mBtnAddNewCard.text = getString(R.string.str_add_more) + "（1）"
    }

    // initialize events of views
    private fun initEvents() {
        // click on the add-new-card button
        mBtnAddNewCard.setOnClickListener {
            if (mLlyCardsContainer.childCount == MAX_NUMBER_OF_NEW_ITEMS)
                Snackbar.make(mBtnAddNewCard, getString(R.string.str_attention_of_maximum_new_items_prefix) + MAX_NUMBER_OF_NEW_ITEMS.toString() + getString(R.string.str_attention_of_maximum_new_items_suffix), Snackbar.LENGTH_SHORT).show()
            else {
                // add a new card
                addNewCard()
                // change the text of this button
                mBtnAddNewCard.text = getString(R.string.str_add_more) + "（${mLlyCardsContainer.childCount}）"
            }
        }

        // click on the cancel button or the submit button
        mCancelSubmitButtonBar.setOnButtonBarClickListener(object: OnButtonBarClickListener {
            // cancelled
            override fun onCancelClickListener() {
                setResult(Activity.RESULT_CANCELED)
                NewItemFieldsManager.reset()
                finish()
            }

            // submitted
            override fun onSubmitClickListener() {
                setResult(Activity.RESULT_OK)

                NewItemFieldsManager.reset()
                mLlyCardsContainer.children.forEach { card ->
                    if (card is BaseItemCard)
                        NewItemFieldsManager.addNewItemFields(card.getAllFields())
                }

                finish()
            }
        })
    }

    // add a new item card
    private fun addNewCard() {
        // create a new item-card whose type is determined by the item-type of the passed intent
        val card = if (intent.getStringExtra(ItemType::name.toString()) == ItemType.FOLDER.name)
            FolderItemCard(this)
        else
            VocabularyItemCard(this)

        // set the listener & title of the card
        card.setOnActionsClickListener(object: OnActionsClickListener {
            override fun onGoUpClickListener() {
                val indexOfThisCard = mLlyCardsContainer.indexOfChild(card)
                if (indexOfThisCard <= 0) return
                mLlyCardsContainer.removeViewAt(indexOfThisCard)
                mLlyCardsContainer.addView(card, indexOfThisCard - 1)
                (mLlyCardsContainer.getChildAt(indexOfThisCard) as BaseItemCard).setTitle(String.format("%02d", indexOfThisCard + 1))
                (mLlyCardsContainer.getChildAt(indexOfThisCard - 1) as BaseItemCard).setTitle(String.format("%02d", indexOfThisCard))
            }

            override fun onGoDownClickListener() {
                val indexOfThisCard = mLlyCardsContainer.indexOfChild(card)
                if (indexOfThisCard < 0 || indexOfThisCard >= mLlyCardsContainer.children.toList().size - 1) return
                mLlyCardsContainer.removeViewAt(indexOfThisCard)
                mLlyCardsContainer.addView(card, indexOfThisCard + 1)
                (mLlyCardsContainer.getChildAt(indexOfThisCard) as BaseItemCard).setTitle(String.format("%02d", indexOfThisCard + 1))
                (mLlyCardsContainer.getChildAt(indexOfThisCard + 1) as BaseItemCard).setTitle(String.format("%02d", indexOfThisCard + 2))
            }

            override fun onCloseClickListener() {
                // get the index of this to-be-removed card
                val indexOfThisCard = mLlyCardsContainer.indexOfChild(card)
                // remove the selected card
                mLlyCardsContainer.removeViewAt(indexOfThisCard)
                // disable the submit button iff the number of cards is zero
                mCancelSubmitButtonBar.setEnabilities(isBtnSubmitEnabled = mLlyCardsContainer.childCount > 0)
                // update the text of the add-new-card button
                mBtnAddNewCard.text = getString(R.string.str_add_more) + "（${mLlyCardsContainer.childCount}）"
                // update titles of all cards below this removed card
                for (idx in indexOfThisCard until mLlyCardsContainer.childCount)
                    (mLlyCardsContainer.getChildAt(idx) as BaseItemCard).setTitle(String.format("%02d", idx + 1))
            }
        })
        card.setTitle(String.format("%02d", mLlyCardsContainer.childCount + 1))

        // add this card and enable the submit button
        mLlyCardsContainer.addView(card)
        mCancelSubmitButtonBar.setEnabilities(isBtnSubmitEnabled = true)
    }

    // remove a certain item card
    private fun removeCard() {

    }
}
