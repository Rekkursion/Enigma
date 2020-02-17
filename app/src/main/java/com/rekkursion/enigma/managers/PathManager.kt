package com.rekkursion.enigma.managers

import android.graphics.Color
import com.rekkursion.enigma.models.BaseItem
import com.rekkursion.pathview.PathView

object PathManager {
    // the path view
    private var mPathView: PathView? = null

    // the list for the recycler-view
    private val mCurrentItemListForRecv: ArrayList<BaseItem> = arrayListOf()
    val itemListForRecv get() = mCurrentItemListForRecv

    /* =================================================================== */

    // set the path view
    fun setPathView(pathView: PathView, defaultNodes: ArrayList<String>? = mPathView?.getAllPathNodes()) {
        mPathView = pathView
        mPathView?.separatorColor = Color.BLACK
        if (defaultNodes != null)
            mPathView?.pushAll(defaultNodes)
    }

    // update the list which is used for the recycler-view
    fun updateListForRecv() {
        mCurrentItemListForRecv.clear()
        mCurrentItemListForRecv.addAll(DataManager.getAllItemsAtCertainPath(getCurrentPath()))
    }

    // push a path node to the tail
    fun pushPathNode(nodeString: String) {
        mPathView?.push(nodeString)
        updateListForRecv()
    }

    // pop a path node from the tail
    fun popPathNode(): String? {
        if (mPathView?.getAllPathNodes()?.isNotEmpty() == true) {
            val lastNode = mPathView?.getAllPathNodes()?.last() ?: return null
            mPathView?.pop()
            updateListForRecv()
            return lastNode
        }
        return null
    }

    // combine the current path nodes and get the current path string
    fun getCurrentPath(): String = mPathView?.getAllPathNodes()?.joinToString("/") ?: ""

    // get all nodes
    fun getAllPathNodes(): ArrayList<String> = mPathView?.getAllPathNodes() ?: arrayListOf()
}