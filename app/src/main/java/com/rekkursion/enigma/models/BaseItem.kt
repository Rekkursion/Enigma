package com.rekkursion.enigma.models

import android.content.Context
import com.rekkursion.enigma.R
import com.rekkursion.enigma.enums.ItemType
import java.io.Serializable
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

abstract class BaseItem: Serializable {
    companion object {
        // for serialization
        private const val serialVersionUID = 306125L
        // for formatting the date-time
        private const val DATE_TIME_FORMATTER_PATTERN = "yyyy-MM-dd HH:mm:ss"
        // for converting path nodes to the path string
        const val PATH_SEPARATOR = "\\"
    }

    /* =================================================================== */

    // the unique id of every instance
    protected val mId: String = UUID.randomUUID().toString()
    val id get() = mId

    // the item-type (FOLDER or VOCABULARY)
    protected abstract val mItemType: ItemType
    val type get() = mItemType

    // the nodes of this item's path
    protected val mPathNodes: ArrayList<String> = ArrayList()
    val pathNodesCopied: ArrayList<String> get() = ArrayList(mPathNodes)
    val pathString: String get() = mPathNodes.joinToString(PATH_SEPARATOR)

    // the local-date-time when this item was created
    protected val mCreateLocalDateTime: LocalDateTime = LocalDateTime.now()

    // the local-date-time at the latest modification
    protected var mLastModifiedLocalDateTime = mCreateLocalDateTime
    var lastModifiedLocalDateTime get() = mLastModifiedLocalDateTime; set(value) { mLastModifiedLocalDateTime = value }

    // the count of the chakans
    protected var mChakanCount: Int = 0
    var chakanCount get() = mChakanCount; set(value) { mChakanCount = value }

    /* =================================================================== */

    // get the identifier of this item
    abstract fun getIdentifier(): String

    // get the summary of this item
    abstract fun getSummary(context: Context): String

    /* =================================================================== */

    // get the base summary
    protected fun getBaseSummary(context: Context): String =
        "${context.getString(R.string.str_base_item_summary_item_type)}${if (mItemType == ItemType.FOLDER) context.getString(R.string.str_folder) else context.getString(R.string.str_vocabulary)}\n" +
        "${context.getString(R.string.str_base_item_summary_path)}root$PATH_SEPARATOR$pathString${if (pathString.isEmpty()) "" else PATH_SEPARATOR}\n" +
        "${context.getString(R.string.str_base_item_summary_create_time)}${mCreateLocalDateTime.format(DateTimeFormatter.ofPattern(DATE_TIME_FORMATTER_PATTERN))}"

    /* =================================================================== */

    // alter the data from another base-item
    open fun alterFrom(another: BaseItem) {
        if (mItemType != another.mItemType)
            return

        mPathNodes.clear()
        mPathNodes.addAll(another.pathNodesCopied)
        mLastModifiedLocalDateTime = another.mLastModifiedLocalDateTime
        mChakanCount = another.mChakanCount
    }

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