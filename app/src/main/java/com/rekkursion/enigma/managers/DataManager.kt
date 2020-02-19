package com.rekkursion.enigma.managers

import android.content.Context
import android.util.Log
import com.rekkursion.enigma.models.BaseItem
import com.rekkursion.enigma.models.FolderItem
import com.rekkursion.enigma.models.VocabularyItem
import com.rekkursion.enigma.utils.SerializationUtils
import java.io.File

object DataManager {
    // the destination file of base-item serialization
    private const val SERIALIZATION_ITEM_FILENAME: String = ".enigma_serialization_items"

    // the hash-map of base-items, including folder- and/or vocabulary- items
    private val mBaseItemHashMap: HashMap<String, ArrayList<BaseItem>> = hashMapOf()

    /* =================================================================== */

    // save all items by serialization
    fun saveAllItemsBySerialization(context: Context?): Boolean {
        context?.let {
            // serial out the words
            val serialOutFile = File(context.filesDir, SERIALIZATION_ITEM_FILENAME)
            if (!serialOutFile.exists())
                serialOutFile.createNewFile()
            val serialOutSuccessOrNot = SerializationUtils.serialize(mBaseItemHashMap, serialOutFile.path)
            if (!serialOutSuccessOrNot)
                Log.e("add-new-classification", "serial-out failed")
            return serialOutSuccessOrNot
        }
        return false
    }

    // load all items by de-serialization
    fun loadAllItemsByDeSerialization(context: Context?, clearFirst: Boolean) {
        context?.let {
            if (clearFirst) mBaseItemHashMap.clear()
            val serialOutFile = File(context.filesDir, SERIALIZATION_ITEM_FILENAME)
            if (!serialOutFile.exists()) return
            val loaded = SerializationUtils.deSerialize<HashMap<String, ArrayList<BaseItem>>>(serialOutFile.path)
            mBaseItemHashMap.putAll(loaded ?: hashMapOf())

            PathManager.updateListForRecv()
        }
    }

    // add a list of new items
    fun addItems(items: ArrayList<BaseItem>) {
        var shouldUpdatePathManagersItemListForRecv = false
        items.forEach { item ->
            // already have this path
            if (mBaseItemHashMap.containsKey(item.pathString))
                mBaseItemHashMap[item.pathString]!!.add(item)
            // didn't have this path, add the copied one
            else
                mBaseItemHashMap[item.pathString] = arrayListOf(item)

            // if there's any item added at the current path, an update on path-manager is a must
            if (!shouldUpdatePathManagersItemListForRecv && item.pathString == PathManager.getCurrentPath())
                shouldUpdatePathManagersItemListForRecv = true
        }

        // update the list for recv on path-manager if needs
        if (shouldUpdatePathManagersItemListForRecv) PathManager.updateListForRecv()
    }

    // get all items at a certain path
    fun getAllItemsAtCertainPath(pathString: String): ArrayList<BaseItem> = mBaseItemHashMap.getOrDefault(pathString, arrayListOf())

    // check if it contains a certain folder at a certain path
    fun containsFolderAtCertainPath(folderName: String, pathString: String = PathManager.getCurrentPath()): Boolean = getAllItemsAtCertainPath(pathString)
        .map { (it as? FolderItem)?.folderName }
        .contains(folderName)

    // check if it contains a certain vocabulary at a global range
    fun containsVocabulary(english: String): Boolean = mBaseItemHashMap
        .map { (_, itemList) ->
            itemList
                .map { (it as? VocabularyItem)?.english }
                .contains(english)
        }
        .contains(true)
}