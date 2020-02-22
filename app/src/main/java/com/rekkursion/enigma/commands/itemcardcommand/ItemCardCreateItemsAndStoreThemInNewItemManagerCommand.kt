package com.rekkursion.enigma.commands.itemcardcommand

import android.content.Context
import androidx.appcompat.widget.LinearLayoutCompat
import com.rekkursion.enigma.managers.NewItemManager
import com.rekkursion.enigma.views.itemcard.BaseItemCard

class ItemCardCreateItemsAndStoreThemInNewItemManagerCommand(context: Context, cardContainer: LinearLayoutCompat): ItemCardCommand(context, cardContainer) {
    /**
     * @param args: varargs Any? -> varargs BaseItemCard (FolderItemCard or VocabularyItemCard)
     */
    override fun execute(vararg args: Any?) {
        // clear the new-item-list
        NewItemManager.reset()

        // add all of the new items
        args.forEach { card ->
            // if it's a folder-item-card or a vocabulary-item-card (a base-item-card)
            if (card is BaseItemCard) {
                // create a new item (folder or vocabulary)
                val newItem = card.createItemModel()

                // add the created new item into the new-item-list
                NewItemManager.addNewItem(newItem)
            }
        }
    }
}