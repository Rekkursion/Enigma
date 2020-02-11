package com.rekkursion.enigma.models

import com.rekkursion.enigma.enums.ItemType
import java.time.LocalDateTime

/**
 * 6 members in the superclass: id, item-type, path-nodes, create-local-date-time, last-modified-local-date-time, chakan-count
 */
class FolderItem: BaseItem() {
    // override the item-type as a FOLDER type
    override val mItemType: ItemType = ItemType.FOLDER

    // override the create-local-date-time and set it to now
    override val mCreateLocalDateTime: LocalDateTime = LocalDateTime.now()

    // the folder's name
    private var mFolderName: String = ""
    var folderName get() = mFolderName; set(value) { mFolderName = value }

    // all vocabularies in this folder
    private val mVocabularyList = ArrayList<VocabularyItem>()
    val vocabularyListCopied get() = ArrayList(mVocabularyList)
}