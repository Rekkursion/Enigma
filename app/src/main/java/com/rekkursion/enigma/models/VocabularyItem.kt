package com.rekkursion.enigma.models

import com.rekkursion.enigma.enums.ItemType
import java.time.LocalDateTime

/**
 * 6 members in the superclass: id, item-type, path-nodes, create-local-date-time, last-modified-local-date-time, chakan-count
 */
class VocabularyItem: BaseItem() {
    // override the item-type as a VOCABULARY type
    override val mItemType: ItemType = ItemType.VOCABULARY

    // override the create-local-date-time and set it to now
    override val mCreateLocalDateTime: LocalDateTime = LocalDateTime.now()

    // the english of this word
    private var mEnglish: String = ""
    var english get() = mEnglish; set(value) { mEnglish = value }

    // the meanings
    private val mMeaningList = ArrayList<Meaning>()
    val meaningListCopied get() = ArrayList(mMeaningList)

    // the proficiency (shouliandu) of this word
    private var mProficiency: Float = 0F
    var proficiency get() = mProficiency; set(value) { mProficiency = value }

    // the remark (beizhu) of this word
    private var mRemark: String = ""
    var remark get() = mRemark; set(value) { mRemark = value }
}