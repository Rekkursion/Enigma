package com.rekkursion.enigma.managers

import com.rekkursion.enigma.models.BaseItem

object PathManager {
    // the current location of path
    private var mCurrentPathNodes: ArrayList<String> = arrayListOf()

    // the list for the recycler-view
    private val mCurrentItemListForRecv: ArrayList<BaseItem> = arrayListOf()
    val itemListForRecv get() = mCurrentItemListForRecv

    /* =================================================================== */

    // update the list which is used for the recycler-view
    fun updateListForRecv() {
        mCurrentItemListForRecv.clear()
        mCurrentItemListForRecv.addAll(DataManager.getAllItemsAtCertainPath(getCurrentPath()))
    }

    // push a path node to the tail
    fun pushPathNode(nodeString: String) {
        mCurrentPathNodes.add(nodeString)
        updateListForRecv()
    }

    // pop a path node from the tail
    fun popPathNode(): String? {
        if (mCurrentPathNodes.isNotEmpty()) {
            val lastNode = mCurrentPathNodes.last()
            mCurrentPathNodes.removeAt(mCurrentPathNodes.size - 1)
            updateListForRecv()
            return lastNode
        }
        return null
    }

    // combine the current path nodes and get the current path string
    fun getCurrentPath(): String = mCurrentPathNodes.joinToString("/")
}