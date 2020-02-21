package com.rekkursion.enigma.commands.itemcardcommand

import android.content.Context
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.LinearLayoutCompat
import com.rekkursion.enigma.enums.ItemType
import com.rekkursion.enigma.listeners.OnActionsClickListener
import com.rekkursion.enigma.managers.CommandManager
import com.rekkursion.enigma.views.CancelOrSubmitButtonBar
import com.rekkursion.enigma.views.itemcard.FolderItemCard
import com.rekkursion.enigma.views.itemcard.VocabularyItemCard

class ItemCardAddCommand(context: Context, cardContainer: LinearLayoutCompat): ItemCardCommand(context, cardContainer) {
    /**
     * @param args: varargs Any? {
     *      1. ItemType: the type of the item-card to be added (folder-item-card or vocabulary-item-card)
     *      2. CancelOrSubmitButtonBar: to be altered if needs
     *      3. Button: the btn-add-new-card, to be altered if needs
     * }
     */
    override fun execute(vararg args: Any?) {
        val itemType = args[0] as ItemType
        val cancelOrSubmitButtonBar = args[1] as CancelOrSubmitButtonBar
        val btnAddNewCard = args[2] as Button

        // create a new item-card whose type is determined by the item-type of the passed intent
        val card = if (itemType == ItemType.FOLDER)
            FolderItemCard(mContext)
        else
            VocabularyItemCard(mContext)

        // set the listener & title of the card
        card.setOnActionsClickListener(object: OnActionsClickListener {
            override fun onGoUpClickListener() {
                CommandManager.doCommand(ItemCardGoUpOrGoDownCommand::class, card, true)
            }

            override fun onGoDownClickListener() {
                CommandManager.doCommand(ItemCardGoUpOrGoDownCommand::class, card, false)
            }

            override fun onCloseClickListener() {
                CommandManager.doCommand(ItemCardRemoveCommand::class, card, cancelOrSubmitButtonBar, btnAddNewCard)
            }
        })
        card.setTitle(String.format("%02d", mCardContainer.childCount + 1))

        // add this card and enable the submit button
        mCardContainer.addView(card)
        cancelOrSubmitButtonBar.setEnabilities(isBtnSubmitEnabled = true)

        // focus on the newest card's first edit-text
        card.getAllFieldsContentViews().filter { it is EditText }.getOrNull(0)?.requestFocus()
    }
}