package com.rekkursion.enigma.activities

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.children
import com.rekkursion.enigma.R
import com.rekkursion.enigma.enums.ItemType
import com.rekkursion.enigma.listeners.OnButtonBarClickListener
import com.rekkursion.enigma.listeners.OnItemCardCloseListener
import com.rekkursion.enigma.managers.NewItemFieldsManager
import com.rekkursion.enigma.views.ItemCard.BaseItemCard
import com.rekkursion.enigma.views.CancelOrSubmitButtonBar
import com.rekkursion.enigma.views.ItemCard.FolderItemCard

class NewItemActivity: AppCompatActivity() {
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
    }

    // initialize events of views
    private fun initEvents() {
        // click on the add-new-card button
        mBtnAddNewCard.setOnClickListener { addNewCard() }

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
        if (intent.getStringExtra(ItemType::name.toString()) == ItemType.FOLDER.name) {
            val card = FolderItemCard(this)
            card.setOnItemCardCloseListener(object: OnItemCardCloseListener {
                override fun onItemCardClose() {
                    mLlyCardsContainer.removeView(card)
                    mCancelSubmitButtonBar.setEnabilities(isBtnSubmitEnabled = mLlyCardsContainer.childCount > 0)
                }
            })
            mLlyCardsContainer.addView(card)
        }
        else {
            val txtv = TextView(this)
            txtv.text = "loser"
            mLlyCardsContainer.addView(txtv)
        }

        mCancelSubmitButtonBar.setEnabilities(isBtnSubmitEnabled = mLlyCardsContainer.childCount > 0)
    }
}
