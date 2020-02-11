package com.rekkursion.enigma.models

import com.rekkursion.enigma.enums.ItemType
import java.io.Serializable
import java.time.LocalDateTime
import java.util.*
import kotlin.collections.ArrayList

abstract class BaseItem: Serializable {
    companion object {
        private const val serialVersionUID = 8829975621220483374L
        private const val DATE_TIME_FORMATTER_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS"
    }

    /* =================================================================== */

    // the unique id of every instance
    protected val mId: String = UUID.randomUUID().toString()

    // the item-type (FOLDER or VOCABULARY)
    protected abstract val mItemType: ItemType
    val type get() = mItemType

    // the nodes of this item's path
    protected val mPathNodes: ArrayList<String> = ArrayList()
    val pathNodesCopied: ArrayList<String> get() = ArrayList(mPathNodes)

    // the local-date-time when this item was created
    protected val mCreateLocalDateTime: LocalDateTime = LocalDateTime.now()

    // the local-date-time at the latest modification
    protected var mLastModifiedLocalDateTime = mCreateLocalDateTime
    var lastModifiedLocalDateTime get() = mLastModifiedLocalDateTime; set(value) { mLastModifiedLocalDateTime = value }

    // the count of the chakans
    protected var mChakanCount: Int = 0
    var chakanCount get() = mChakanCount; set(value) { mChakanCount = value }

    /* =================================================================== */

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as BaseItem

        if (mId != other.mId) return false
        if (mItemType != other.mItemType) return false

        return true
    }

    override fun hashCode(): Int {
        var result = mId.hashCode()
        result = 31 * result + mItemType.hashCode()
        return result
    }
}