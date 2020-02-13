package com.rekkursion.enigma.views.ItemCard

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.children
import com.rekkursion.enigma.R
import com.rekkursion.enigma.listeners.OnItemCardCloseListener
import com.rekkursion.enigma.views.ItemCardField

@Suppress("LeakingThis")
abstract class BaseItemCard(context: Context, attrs: AttributeSet?): FrameLayout(context, attrs) {
    // the linear-layout for placing all fields
    protected val mLlyFieldsContainer: LinearLayoutCompat

    // the imgbtn for closing the item-card
    protected val mImgbtnClose: ImageButton

    // the on-item-card-close-listener
    protected var mOnItemCardCloseListener: OnItemCardCloseListener? = null

    /* =================================================================== */

    // primary constructor
    init {
        // inflate the layout
        LayoutInflater.from(context).inflate(R.layout.view_base_item_card, this)

        // get views that are in the base item's layout
        mLlyFieldsContainer = findViewById(R.id.lly_fields_container)
        mImgbtnClose = findViewById(R.id.imgbtn_close_item_card)
    }

    /* =================================================================== */

    // set the fields determined by the type of the item, i.e., folder or vocabulary
    protected abstract fun setFields()

    /* =================================================================== */

    // set the on-item-card-close-listener
    fun setOnItemCardCloseListener(onItemCardCloseListener: OnItemCardCloseListener) {
        mOnItemCardCloseListener = onItemCardCloseListener
    }

    // get all fields and their corresponding content views
    fun getAllFields(): HashMap<String, View?> {
        val ret = HashMap<String, View?>()
        mLlyFieldsContainer.children.forEach { itemCardField ->
            if (itemCardField is ItemCardField)
                ret[itemCardField.fieldName] = itemCardField.fieldContentView
        }
        return ret
    }
}