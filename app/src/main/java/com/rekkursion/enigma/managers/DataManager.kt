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
        }
    }

    // add a list of new items
    fun addItems(items: ArrayList<BaseItem>) {
        items.forEach { item ->
            // already have this path
            if (mBaseItemHashMap.containsKey(item.pathString))
                mBaseItemHashMap[item.pathString]!!.add(item)
            // didn't have this path, add the copied one
            else
                mBaseItemHashMap[item.pathString] = arrayListOf(item)

            // find the folder we are currently staying to add the item into it
            item.getStayingFolder()?.addItem(item)
        }
    }

    // remove a certain item
    fun removeItem(item: BaseItem) {
        val list = mBaseItemHashMap[item.pathString]
        val itemInList = list?.find { it == item }
        itemInList?.let {
            // remove it from the list of hash-map in data-manager
            list.remove(itemInList)
            // find the folder we are currently staying to remove the item from it
            itemInList.getStayingFolder()?.removeItem(itemInList)
            // remove all lists in data-manager due to the removing of this folder-item
            if (itemInList is FolderItem) {
                Thread {
                    val extendedPathStr = (item.pathString + BaseItem.PATH_SEPARATOR + itemInList.folderName).trim(BaseItem.PATH_SEPARATOR[0])
                    val filtered = mBaseItemHashMap.filterKeys { it.startsWith(extendedPathStr) }
                    filtered.forEach { (key, _) -> mBaseItemHashMap.remove(key) }
                }.start()
            }
        }
    }

    // rename a certain folder-item
    fun renameFolderItem(folderItem: FolderItem, newFolderName: String) {
        val itemInList = mBaseItemHashMap[folderItem.pathString]?.find { it == folderItem } as? FolderItem
        itemInList?.let {
            itemInList.updateFolderName(newFolderName)
        }
    }

    // get all items at a certain path
    fun getAllItemsAtCertainPath(pathString: String): ArrayList<BaseItem> = mBaseItemHashMap.getOrDefault(pathString, arrayListOf())

    // get all vocabulary-items at a certain path
    fun getAllVocabularyItemsAtCertainPath(pathString: String): ArrayList<VocabularyItem>
            = getAllItemsAtCertainPath(pathString).filter { it is VocabularyItem }.map { it as VocabularyItem }.toCollection(ArrayList())

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

    // get a certain folder at a certain path
    fun getFolderAtCertainPath(folderName: String, pathString: String): FolderItem? = getAllItemsAtCertainPath(pathString)
        .find { it is FolderItem && it.folderName == folderName } as? FolderItem

    // replace the path string with a new path string
    fun replacePaths(oldPathString: String, newPathString: String) {
        val oldList = mBaseItemHashMap[oldPathString]
        val newList = mBaseItemHashMap[newPathString]
        newList?.clear()
        if (oldList != null) {
            mBaseItemHashMap[newPathString] = ArrayList(oldList)
            mBaseItemHashMap.remove(oldPathString)
        }
    }
}