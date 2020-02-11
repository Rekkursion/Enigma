package com.rekkursion.enigma.models

import com.rekkursion.enigma.enums.ItemType

/**
 * 6 members in the superclass: id, item-type, path-nodes, create-local-date-time, last-modified-local-date-time, chakan-count
 */
class FolderItem(
    pathNodes: ArrayList<String>,
    folderName: String,
    vocabularies: ArrayList<VocabularyItem> = arrayListOf()
): BaseItem() {
    // override the item-type as a FOLDER type
    override val mItemType: ItemType = ItemType.FOLDER

    /* =================================================================== */

    // the folder's name
    private var mFolderName: String = folderName
    var folderName get() = mFolderName; set(value) { mFolderName = value }

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
}