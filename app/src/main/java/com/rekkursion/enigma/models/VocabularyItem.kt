package com.rekkursion.enigma.models

import com.rekkursion.enigma.enums.ItemType

/**
 * 6 members in the superclass: id, item-type, path-nodes, create-local-date-time, last-modified-local-date-time, chakan-count
 */
class VocabularyItem(
    pathNodes: ArrayList<String>,
    english: String,
    meanings: ArrayList<Meaning>,
    proficiency: Float,
    remark: String = ""
): BaseItem() {
    // override the item-type as a VOCABULARY type
    override val mItemType: ItemType = ItemType.VOCABULARY

    /* =================================================================== */

    // the english of this word
    private var mEnglish: String = english
    var english get() = mEnglish; set(value) { mEnglish = value }

    // the meanings
    private val mMeaningList = ArrayList<Meaning>(meanings)
    val meaningListCopied get() = ArrayList(mMeaningList)

    // the proficiency (shouliandu) of this word
    private var mProficiency: Float = proficiency
    var proficiency get() = mProficiency; set(value) { mProficiency = value }

    // the remark (beizhu) of this word
    private var mRemark: String = remark
    var remark get() = mRemark; set(value) { mRemark = value }

    /* =================================================================== */

    // primary constructor
    init {
        mPathNodes.clear()
        mPathNodes.addAll(pathNodes)
    }
}