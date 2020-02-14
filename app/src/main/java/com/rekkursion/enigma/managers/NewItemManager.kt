package com.rekkursion.enigma.managers

import com.rekkursion.enigma.models.BaseItem

object NewItemManager {
    // to store all new items' fields
    private val mNewItemList = ArrayList<BaseItem>()
    val newItemList get() = mNewItemList

    /* =================================================================== */

    // re-set the new-item list, i.e., clear it
    fun reset() {
        mNewItemList.clear()
    }

    // add a new item
    fun addNewItem(newItem: BaseItem) {
        mNewItemList.add(newItem)
    }
}