package com.rekkursion.enigma.commands.itemcardcommand

import android.content.Context
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.children
import com.rekkursion.enigma.views.itemcard.BaseItemCard

class ItemCardGoUpOrGoDownCommand(context: Context, cardContainer: LinearLayoutCompat): ItemCardCommand(context, cardContainer) {
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
        val indexOfThisCard = mCardContainer.indexOfChild(card)
        if (indexOfThisCard <= 0) return
        mCardContainer.removeViewAt(indexOfThisCard)
        mCardContainer.addView(card, indexOfThisCard - 1)
        (mCardContainer.getChildAt(indexOfThisCard) as BaseItemCard).setTitle(String.format("%02d", indexOfThisCard + 1))
        (mCardContainer.getChildAt(indexOfThisCard - 1) as BaseItemCard).setTitle(String.format("%02d", indexOfThisCard))
    }

    // go down
    private fun goDown(card: BaseItemCard) {
        val indexOfThisCard = mCardContainer.indexOfChild(card)
        if (indexOfThisCard < 0 || indexOfThisCard >= mCardContainer.children.toList().size - 1) return
        mCardContainer.removeViewAt(indexOfThisCard)
        mCardContainer.addView(card, indexOfThisCard + 1)
        (mCardContainer.getChildAt(indexOfThisCard) as BaseItemCard).setTitle(String.format("%02d", indexOfThisCard + 1))
        (mCardContainer.getChildAt(indexOfThisCard + 1) as BaseItemCard).setTitle(String.format("%02d", indexOfThisCard + 2))
    }
}