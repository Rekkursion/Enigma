package com.rekkursion.enigma.commands

import android.annotation.SuppressLint
import com.rekkursion.enigma.R
import com.rekkursion.enigma.activities.NewItemActivity
import com.rekkursion.enigma.views.itemcard.BaseItemCard

class ItemCardRemoveCommand(newItemActivity: NewItemActivity): ItemCardCommand {
    override val mNewItemActivityInstance: NewItemActivity = newItemActivity

    /**
     * @param args: varargs Any? {
     *      1. BaseItemCard: the item-card which is about to be removed
     * }
     */
    @SuppressLint("SetTextI18n")
    override fun execute(vararg args: Any?) {
        val card = args[0] as BaseItemCard

        // get the index of this to-be-removed card
        val indexOfThisCard = mNewItemActivityInstance.mLlyCardsContainer.indexOfChild(card)
        // remove the selected card
        mNewItemActivityInstance.mLlyCardsContainer.removeViewAt(indexOfThisCard)
        // disable the submit button iff the number of cards is zero
        mNewItemActivityInstance.mCancelSubmitButtonBar.setEnabilities(isBtnSubmitEnabled = mNewItemActivityInstance.mLlyCardsContainer.childCount > 0)
        // update the text of the add-new-card button
        mNewItemActivityInstance.mBtnAddNewCard.text = mNewItemActivityInstance.getString(R.string.str_add_more) + "（${mNewItemActivityInstance.mLlyCardsContainer.childCount}）"
        // update titles of all cards below this removed card
        for (idx in indexOfThisCard until mNewItemActivityInstance.mLlyCardsContainer.childCount)
            (mNewItemActivityInstance.mLlyCardsContainer.getChildAt(idx) as BaseItemCard).setTitle(String.format("%02d", idx + 1))
    }
}