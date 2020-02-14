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
import com.rekkursion.enigma.commands.BaseCommand
import com.rekkursion.enigma.commands.ItemCardAddCommand
import com.rekkursion.enigma.commands.ItemCardGoUpOrGoDownCommand
import com.rekkursion.enigma.commands.ItemCardRemoveCommand
import com.rekkursion.enigma.enums.ItemType
import com.rekkursion.enigma.listeners.OnActionsClickListener
import com.rekkursion.enigma.listeners.OnButtonBarClickListener
import com.rekkursion.enigma.managers.NewItemFieldsManager
import com.rekkursion.enigma.views.itemcard.BaseItemCard
import com.rekkursion.enigma.views.CancelOrSubmitButtonBar
import com.rekkursion.enigma.views.itemcard.FolderItemCard
import com.rekkursion.enigma.views.itemcard.VocabularyItemCard
import java.util.HashMap

@SuppressLint("SetTextI18n")
class NewItemActivity: AppCompatActivity() {
    companion object {
        private const val MAX_NUMBER_OF_NEW_ITEMS = 99
    }

    /* ================================================================== */

    // the cancel & submit button-bar
    internal lateinit var mCancelSubmitButtonBar: CancelOrSubmitButtonBar

    // the container for placing item-cards
    internal lateinit var mLlyCardsContainer: LinearLayoutCompat

    // the button for adding a new item-cards
    internal lateinit var mBtnAddNewCard: Button

    // some commands for item-card operations
    private val mCommands = HashMap<String, BaseCommand>()

    /* ================================================================== */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_item)

        initViews()
        initCommands()
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

    // initialize commands
    private fun initCommands() {
        // command of adding a new item-card
        mCommands[ItemCardAddCommand::class.java.name] = ItemCardAddCommand(this)
        // command of removing a certain item-card
        mCommands[ItemCardRemoveCommand::class.java.name] = ItemCardRemoveCommand(this)
        // command of going-up or going-down of a certain item-card
        mCommands[ItemCardGoUpOrGoDownCommand::class.java.name] = ItemCardGoUpOrGoDownCommand(this)
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
        getCommand(ItemCardAddCommand::class.java.name)?.execute()
    }

    /* ================================================================== */

    // get the designated command
    internal fun getCommand(commandSelector: String): BaseCommand? = mCommands[commandSelector]
}
