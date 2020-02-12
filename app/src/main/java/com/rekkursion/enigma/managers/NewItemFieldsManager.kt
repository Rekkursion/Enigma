package com.rekkursion.enigma.managers

import android.view.View

object NewItemFieldsManager {
    // to store all new items' fields
    private val mNewItemList = ArrayList<HashMap<String, ArrayList<View>>>()
    val newItemsFieldsList get() = mNewItemList

    /* =================================================================== */

    // re-set the new-item list, i.e., clear it
    fun reset() { mNewItemList.clear() }

    // add new item's fields
    fun addNewItemFields(fieldsHashMap: HashMap<String, ArrayList<View>>) { mNewItemList.add(fieldsHashMap) }
}