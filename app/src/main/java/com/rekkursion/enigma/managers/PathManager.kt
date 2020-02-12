package com.rekkursion.enigma.managers

import com.rekkursion.enigma.models.BaseItem

object PathManager {
    // the current location of path
    private var mCurrentPathNodes: ArrayList<String> = arrayListOf()

    // the base-items at the current path
    private val mCurrentBaseItems: ArrayList<BaseItem> = arrayListOf()
    val currentBaseItems get() = mCurrentBaseItems

    /* =================================================================== */

    // when path-nodes are changed, the current base-items shall be updated as well
    internal fun updateCurrentBaseItems() {
        val path = mCurrentPathNodes.joinToString("/")
        mCurrentBaseItems.clear()
        mCurrentBaseItems.addAll(DataManager.baseItemHashMap.getOrElse(path) { arrayListOf() })
    }
}