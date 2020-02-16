package com.rekkursion.enigma.commands.itemcardcommand

import androidx.core.view.children
import com.rekkursion.enigma.activities.NewItemActivity
import com.rekkursion.enigma.views.itemcard.BaseItemCard

class ItemCardGoUpOrGoDownCommand(newItemActivity: NewItemActivity): ItemCardCommand(newItemActivity) {
    /**
     * @param args: varargs Any? {
     *      1. BaseItemCard: the item-card which is about to be moved (go up or go down)
     *      2. Boolean: true = go up,   false = go down
     * }
     */
    override fun execute(vararg args: Any?) {
        val card = args[0] as BaseItemCard
        val isGoUp = args[1] as Boolean
        if (isGoUp) goUp(card) else goDown(card)
    }

    // go up
    private fun goUp(card: BaseItemCard) {
        val indexOfThisCard = mNewItemActivityInstance.mLlyCardsContainer.indexOfChild(card)
        if (indexOfThisCard <= 0) return
        mNewItemActivityInstance.mLlyCardsContainer.removeViewAt(indexOfThisCard)
        mNewItemActivityInstance.mLlyCardsContainer.addView(card, indexOfThisCard - 1)
        (mNewItemActivityInstance.mLlyCardsContainer.getChildAt(indexOfThisCard) as BaseItemCard).setTitle(String.format("%02d", indexOfThisCard + 1))
        (mNewItemActivityInstance.mLlyCardsContainer.getChildAt(indexOfThisCard - 1) as BaseItemCard).setTitle(String.format("%02d", indexOfThisCard))
    }

    // go down
    private fun goDown(card: BaseItemCard) {
        val indexOfThisCard = mNewItemActivityInstance.mLlyCardsContainer.indexOfChild(card)
        if (indexOfThisCard < 0 || indexOfThisCard >= mNewItemActivityInstance.mLlyCardsContainer.children.toList().size - 1) return
        mNewItemActivityInstance.mLlyCardsContainer.removeViewAt(indexOfThisCard)
        mNewItemActivityInstance.mLlyCardsContainer.addView(card, indexOfThisCard + 1)
        (mNewItemActivityInstance.mLlyCardsContainer.getChildAt(indexOfThisCard) as BaseItemCard).setTitle(String.format("%02d", indexOfThisCard + 1))
        (mNewItemActivityInstance.mLlyCardsContainer.getChildAt(indexOfThisCard + 1) as BaseItemCard).setTitle(String.format("%02d", indexOfThisCard + 2))
    }
}