package com.rekkursion.enigma.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.appcompat.widget.LinearLayoutCompat
import com.rekkursion.enigma.listeners.OnItemCardCloseListener

abstract class BaseItemCard(context: Context, attrs: AttributeSet?): FrameLayout(context, attrs) {
    // the linear-layout for placing all fields
    protected abstract val mLlyFieldsContainer: LinearLayoutCompat

    // the imgbtn for closing the item-card
    protected abstract val mImgbtnClose: ImageButton

    // the on-item-card-close-listener
    protected var mOnItemCardCloseListener: OnItemCardCloseListener? = null

    /* =================================================================== */

    // set the fields determined by the type of the item, i.e., folder or vocabulary
    protected abstract fun setFields()

    /* =================================================================== */

    // set the on-item-card-close-listener
    fun setOnItemCardCloseListener(onItemCardCloseListener: OnItemCardCloseListener) {
        mOnItemCardCloseListener = onItemCardCloseListener
    }
}