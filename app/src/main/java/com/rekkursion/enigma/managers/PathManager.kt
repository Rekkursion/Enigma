package com.rekkursion.enigma.managers

import android.graphics.Color
import com.rekkursion.enigma.models.BaseItem
import com.rekkursion.enigma.models.FolderItem
import com.rekkursion.enigma.models.VocabularyItem
import com.rekkursion.pathview.PathView

object PathManager {
    // the path view
    private var mPathView: PathView? = null

    /* =================================================================== */

    // set the path view
    fun setPathView(pathView: PathView, defaultNodes: ArrayList<String>? = mPathView?.getAllPathNodes()) {
        mPathView = pathView
        mPathView?.separatorColor = Color.BLACK
        if (defaultNodes != null)
            mPathView?.pushAll(defaultNodes)
    }

    // push a path node to the tail
    fun pushPathNode(nodeString: String) {
        mPathView?.push(nodeString)
    }

    // pop a path node from the tail
    fun popPathNode(): String? {
        if (mPathView?.getAllPathNodes()?.isNotEmpty() == true) {
            val lastNode = mPathView?.getAllPathNodes()?.last() ?: return null
            mPathView?.pop()
            return lastNode
        }
        return null
    }

    // combine the current path nodes and get the current path string
    fun getCurrentPath(): String = mPathView?.getAllPathNodes()?.joinToString(BaseItem.PATH_SEPARATOR) ?: ""

    // get all nodes
    fun getAllPathNodes(): ArrayList<String> = mPathView?.getAllPathNodes() ?: arrayListOf()

    // get the last path node of a designated path string
    fun getLastPathNode(pathString: String): String = when {
        pathString.isEmpty() -> ""
        else -> pathString.split(BaseItem.PATH_SEPARATOR).lastOrNull() ?: ""
    }

    // get the last stratum path string
    fun getLastStrataPathString(pathString: String): String? {
        return if (pathString.isEmpty()) null
        else {
            val lastIdxOfSeparator = pathString.lastIndexOf(BaseItem.PATH_SEPARATOR)
            if (lastIdxOfSeparator < 0) ""
            else pathString.substring(0, lastIdxOfSeparator)
        }
    }
}