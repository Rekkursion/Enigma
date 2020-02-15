package com.rekkursion.enigma.commands

import android.annotation.SuppressLint
import android.widget.EditText
import com.rekkursion.enigma.activities.NewItemActivity
import com.rekkursion.enigma.enums.ItemType
import com.rekkursion.enigma.listeners.OnActionsClickListener
import com.rekkursion.enigma.views.itemcard.FolderItemCard
import com.rekkursion.enigma.views.itemcard.VocabularyItemCard

class ItemCardAddCommand(newItemActivity: NewItemActivity): ItemCardCommand {
    override val mNewItemActivityInstance: NewItemActivity = newItemActivity

    /**
     * @param args: *no parameters*
     */
    override fun execute(vararg args: Any?) {
        // create a new item-card whose type is determined by the item-type of the passed intent
        val card = if (mNewItemActivityInstance.intent.getStringExtra(ItemType::name.toString()) == ItemType.FOLDER.name)
            FolderItemCard(mNewItemActivityInstance)
        else
            VocabularyItemCard(mNewItemActivityInstance)

        // set the listener & title of the card
        card.setOnActionsClickListener(object: OnActionsClickListener {
            override fun onGoUpClickListener() {
                mNewItemActivityInstance.getCommand(ItemCardGoUpOrGoDownCommand::class.java.name)?.execute(card, true)
            }

            override fun onGoDownClickListener() {
                mNewItemActivityInstance.getCommand(ItemCardGoUpOrGoDownCommand::class.java.name)?.execute(card, false)
            }

            @SuppressLint("SetTextI18n")
            override fun onCloseClickListener() {
                mNewItemActivityInstance.getCommand(ItemCardRemoveCommand::class.java.name)?.execute(card)
            }
        })
        card.setTitle(String.format("%02d", mNewItemActivityInstance.mLlyCardsContainer.childCount + 1))

        // add this card and enable the submit button
        mNewItemActivityInstance.mLlyCardsContainer.addView(card)
        mNewItemActivityInstance.mCancelSubmitButtonBar.setEnabilities(isBtnSubmitEnabled = true)

        // focus on the newest card's first edit-text
        card.getAllFieldsContentViews().filter { it is EditText }.getOrNull(0)?.requestFocus()
    }
}