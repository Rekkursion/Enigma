package com.rekkursion.enigma.commands.itemcardcommand

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.widget.Button
import androidx.appcompat.widget.LinearLayoutCompat
import com.rekkursion.enigma.R
import com.rekkursion.enigma.views.CancelOrSubmitButtonBar
import com.rekkursion.enigma.views.itemcard.BaseItemCard

class ItemCardRemoveCommand(context: Context, cardContainer: LinearLayoutCompat): ItemCardCommand(context, cardContainer) {
    /**
     * @param args: varargs Any? {
     *      1. BaseItemCard: the item-card which is about to be removed
     *      2. CancelOrSubmitButtonBar: to be altered if needs
     *      3. Button: the btn-add-new-card, to be altered if needs
     * }
     */
    override fun execute(vararg args: Any?) {
        val card = args[0] as BaseItemCard
        val cancelOrSubmitButtonBar = args[1] as CancelOrSubmitButtonBar
        val btnAddNewCard = args[2] as Button

        promptUpDialog(card, cancelOrSubmitButtonBar, btnAddNewCard)
    }
    
    // first, prompt up a dialog to check if the user really want to remove this item-card or not
    private fun promptUpDialog(card: BaseItemCard, cancelOrSubmitButtonBar: CancelOrSubmitButtonBar, btnAddNewCard: Button) {
        AlertDialog.Builder(mContext)
            .setTitle(mContext.getString(R.string.str_attention_of_removing_item_card))
            .setPositiveButton(mContext.getString(R.string.str_confirm)) { _, _ ->
                // if yes, really remove the designated item-card
                reallyRemoveDesignatedCard(card, cancelOrSubmitButtonBar, btnAddNewCard)
            }
            .setNegativeButton(mContext.getString(R.string.str_cancel), null)
            .create()
            .show()
    }

    // remove the designated item-card
    @SuppressLint("SetTextI18n")
    private fun reallyRemoveDesignatedCard(card: BaseItemCard, cancelOrSubmitButtonBar: CancelOrSubmitButtonBar, btnAddNewCard: Button) {
        // get the index of this to-be-removed card
        val indexOfThisCard = mCardContainer.indexOfChild(card)

        // remove the selected card
        mCardContainer.removeViewAt(indexOfThisCard)

        // disable the submit button iff the number of cards is zero
        cancelOrSubmitButtonBar.setEnabilities(isBtnSubmitEnabled = mCardContainer.childCount > 0)

        // update the text of the add-new-card button
        btnAddNewCard.text = mContext.getString(R.string.str_add_more) + "（${mCardContainer.childCount}）"

        // update titles of all cards below this removed card
        for (idx in indexOfThisCard until mCardContainer.childCount)
            (mCardContainer.getChildAt(idx) as BaseItemCard).setTitle(String.format("%02d", idx + 1))
    }
}