package com.rekkursion.enigma.managers

import com.rekkursion.enigma.models.BaseItem

object MovingItemManager {
    // the original path string
    private var mOrigPathString: String? = null

    // the original position in the recycler-view
    private var mOrigPosInRecv: Int? = null

    private var mOrigItem: BaseItem? = null
    val origItem get() = mOrigItem

    // start the moving operation
    fun startMoving(item: BaseItem, originalPathString: String, originalPositionInRecv: Int) {
        mOrigItem = item
        mOrigPathString = originalPathString
        mOrigPosInRecv = originalPositionInRecv
    }
}