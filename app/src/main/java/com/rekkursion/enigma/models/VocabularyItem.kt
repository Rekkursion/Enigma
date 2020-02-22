package com.rekkursion.enigma.models

import android.content.Context
import com.rekkursion.enigma.enums.ItemType

/**
 * 6 members in the superclass: id, item-type, path-nodes, create-local-date-time, last-modified-local-date-time, chakan-count
 */
class VocabularyItem(
    pathNodes: ArrayList<String>,
    english: String,
    meanings: ArrayList<Meaning>,
    proficiency: Float,
    remark: String = "",
    tags: ArrayList<String> = arrayListOf()
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
    val numOfMeanings: Int get() = mMeaningList.size

    // the proficiency (shouliandu) of this word
    private var mProficiency: Float = proficiency
    var proficiency get() = mProficiency; set(value) { mProficiency = value }

    // the remark (beizhu) of this word
    private var mRemark: String = remark
    var remark get() = mRemark; set(value) { mRemark = value }

    // the tags of this word
    private val mTagList = ArrayList<String>(tags)
    val tagListCopied get() = ArrayList(mTagList)

    // if the item on the recycler-view is expanded or not
    private var mIsExpanded: Boolean = false
    var isExpanded get() = mIsExpanded; set(value) { mIsExpanded = value }

    /* =================================================================== */

    // primary constructor
    init {
        mPathNodes.clear()
        mPathNodes.addAll(pathNodes)
    }

    /* =================================================================== */

    // get the identifier of this vocabulary-item (english)
    override fun getIdentifier(): String = mEnglish

    // get the summary of this vocabulary-item
    override fun getSummary(context: Context): String = getBaseSummary(context)

    // alter the data from another vocabulary-item
    override fun alterFrom(another: BaseItem) {
        if (another !is VocabularyItem) return

        super.alterFrom(another)
        mEnglish = another.mEnglish
        mMeaningList.clear()
        mMeaningList.addAll(another.meaningListCopied)
        mProficiency = another.mProficiency
        mRemark = another.mRemark
        mTagList.clear()
        mTagList.addAll(another.tagListCopied)
        mIsExpanded = another.mIsExpanded
    }

    /* =================================================================== */

    // get the meaning by a certain index
    fun getMeaningAt(idx: Int): Meaning? = mMeaningList.getOrNull(idx)
}