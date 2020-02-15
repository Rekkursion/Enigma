package com.rekkursion.enigma.managers

import com.rekkursion.enigma.models.BaseItem

object PathManager {
    // the current location of path
    private var mCurrentPathNodes: ArrayList<String> = arrayListOf()

    // the list for the recycler-view
    private val mCurrentItemListForRecv: ArrayList<BaseItem> = arrayListOf()
    val itemListForRecv get() = mCurrentItemListForRecv

    /* =================================================================== */

    fun updateListForRecv() {
        mCurrentItemListForRecv.clear()
        mCurrentItemListForRecv.addAll(DataManager.getAllItemsAtCertainPath(getCurrentPath()))
    }

    /* =================================================================== */

    // combine the current path nodes and get the current path string
    fun getCurrentPath(): String = mCurrentPathNodes.joinToString("/")
}