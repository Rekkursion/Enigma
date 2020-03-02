package com.rekkursion.enigma.models

import android.content.Context
import com.rekkursion.enigma.R
import com.rekkursion.enigma.enums.ItemType
import com.rekkursion.enigma.managers.DataManager

/**
 * 6 members in the superclass: id, item-type, path-nodes, create-local-date-time, last-modified-local-date-time, chakan-count
 */
class FolderItem(
    pathNodes: ArrayList<String>,
    folderName: String,
    folders: ArrayList<FolderItem> = arrayListOf(),
    vocabularies: ArrayList<VocabularyItem> = arrayListOf()
): BaseItem() {
    // override the item-type as a FOLDER type
    override val mItemType: ItemType = ItemType.FOLDER

    /* =================================================================== */

    // the folder's name
    private var mFolderName: String = folderName
    val folderName get() = mFolderName

    // all folders in this folder
    private val mFolderList = ArrayList(folders)
    val folderListCopied get() = ArrayList(mFolderList)
    val numOfFolders get() = mFolderList.size

    // all vocabularies in this folder
    private val mVocabularyList = ArrayList(vocabularies)
    val vocabularyListCopied get() = ArrayList(mVocabularyList)
    val numOfVocabularies get() = mVocabularyList.size

    /* =================================================================== */

    // primary constructor
    init {
        mPathNodes.clear()
        mPathNodes.addAll(pathNodes)
    }

    /* =================================================================== */

    // get the identifier of this folder-item (folder name)
    override fun getName(): String = mFolderName

    // get the summary of this folder-item
    override fun getSummary(context: Context): String =
        "${getBaseSummary(context)}\n" +
        "${context.getString(R.string.str_folder_item_summary_num_of_folders_prefix)}$numOfFolders${context.getString(R.string.str_folder_item_summary_num_of_folders_suffix)}" +
        "${context.getString(R.string.str_folder_item_summary_num_of_vocabularies_prefix)}$numOfVocabularies${context.getString(R.string.str_folder_item_summary_num_of_vocabularies_suffix)}"

    /* =================================================================== */

    // add a folder-item
    fun addFolder(folder: FolderItem) {
        mFolderList.add(folder)
    }

    // remove a folder-item
    fun removeFolder(folder: FolderItem): Boolean = mFolderList.remove(folder)

    // add a vocabulary-item
    fun addVocabulary(vocabulary: VocabularyItem) {
        mVocabularyList.add(vocabulary)
    }

    // remove a vocabulary-item
    fun removeVocabulary(vocabulary: VocabularyItem): Boolean = mVocabularyList.remove(vocabulary)

    // add a base-item
    fun addItem(item: BaseItem) {
        if (item is FolderItem) addFolder(item)
        else addVocabulary(item as VocabularyItem)
    }

    // remove a base-item
    fun removeItem(item: BaseItem): Boolean = if (item is FolderItem)
        removeFolder(item)
    else
        removeVocabulary(item as VocabularyItem)

    // add all base-items which are in a certain folder
    fun addAllItemsInCertainFolder(folder: FolderItem) {
        folder.mFolderList.forEach { addFolder(it) }
        folder.mVocabularyList.forEach { addVocabulary(it) }
    }

    // clear all folder- and vocabulary- items
    fun clear() {
        mVocabularyList.clear()
        mFolderList.clear()
    }

    // update this folder-item's folder name
    fun updateFolderName(newFolderName: String) {
        // get the original and new extended path strings
        val origExtendedPathStr = (pathString + PATH_SEPARATOR + mFolderName).trim(PATH_SEPARATOR[0])
        val newExtendedPathStr = (pathString + PATH_SEPARATOR + newFolderName).trim(PATH_SEPARATOR[0])

        // recursively update all items in this folder
        Thread {
            updatePathNodesRecursively(origExtendedPathStr, newExtendedPathStr)
        }.start()

        // change the this folder's name
        mFolderName = newFolderName
    }

    // helper function: recursively update all items in a certain folder
    private fun updatePathNodesRecursively(curPathStr: String, newPathStr: String) {
        DataManager.getAllItemsAtCertainPath(curPathStr).forEach {
            if (it is FolderItem)
                updatePathNodesRecursively(
                    (curPathStr + PATH_SEPARATOR + it.folderName).trim(PATH_SEPARATOR[0]),
                    (newPathStr + PATH_SEPARATOR + it.folderName).trim(PATH_SEPARATOR[0])
                )
            it.updatePathNodesByPathString(newPathStr)
        }
        DataManager.replaceCertainPath(curPathStr, newPathStr)
    }
}