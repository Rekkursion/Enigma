package com.rekkursion.enigma.models

import android.content.Context
import com.rekkursion.enigma.R
import com.rekkursion.enigma.enums.ItemType

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
    var folderName get() = mFolderName; set(value) { mFolderName = value }

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
    override fun getIdentifier(): String = mFolderName

    // get the details of this folder-item
    override fun getDetails(context: Context): String =
        "${getBaseDetails(context)}\n" +
        "${context.getString(R.string.str_base_item_details_num_of_vocabularies_prefix)}$numOfVocabularies${context.getString(R.string.str_base_item_details_num_of_vocabularies_suffix)}"

    /* =================================================================== */

    // add a folder-item
    fun addFolder(folder: FolderItem) {
        mFolderList.add(folder)
    }

    // add a vocabulary-item
    fun addVocabulary(vocabulary: VocabularyItem) {
        mVocabularyList.add(vocabulary)
    }

    // add all base-items in a certain folder
    fun addAllItemsInCertainFolder(folder: FolderItem) {
        folder.mFolderList.forEach { addFolder(it) }
        folder.mVocabularyList.forEach { addVocabulary(it) }
    }

    // clear all folder- and vocabulary- items
    fun clear() {
        mVocabularyList.clear()
        mFolderList.clear()
    }
}