package com.rekkursion.enigma.views.itemcard

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.children
import com.rekkursion.enigma.R
import com.rekkursion.enigma.listeners.OnActionsClickListener
import com.rekkursion.enigma.listeners.OnItemCardCloseListener
import com.rekkursion.enigma.views.ActionsTitleImageButtonBar
import com.rekkursion.enigma.views.ItemCardField

@Suppress("LeakingThis")
abstract class BaseItemCard(context: Context, attrs: AttributeSet?): FrameLayout(context, attrs) {
    // the actions bar
    protected val mActionsBar: ActionsTitleImageButtonBar

    // the linear-layout for placing all fields
    protected val mLlyFieldsContainer: LinearLayoutCompat

    /* =================================================================== */

    // primary constructor
    init {
        // inflate the layout
        LayoutInflater.from(context).inflate(R.layout.view_base_item_card, this)

        // get views that are in the base item's layout
        mLlyFieldsContainer = findViewById(R.id.lly_fields_container)
        mActionsBar = findViewById(R.id.actions_title_bar_at_base_item_card)
    }

    /* =================================================================== */

    // initialize all fields which are to be set
    protected abstract fun initFields()

    // set the fields determined by the type of the item, i.e., folder or vocabulary
    protected abstract fun setFields()

    /* =================================================================== */

    // set the listener of actions-title-bar
    fun setOnActionsClickListener(onActionsClickListener: OnActionsClickListener) {
        mActionsBar.setOnActionsClickListener(onActionsClickListener)
    }

    // set the title of actions-title-bar
    fun setTitle(title: String) {
        mActionsBar.setTitle(title)
    }

    // get content views of all fields
    fun getAllFieldsContentViews(): ArrayList<View?> {
        val ret = ArrayList<View?>()
        mLlyFieldsContainer.children.forEach { itemCardField ->
            if (itemCardField is ItemCardField)
                ret.add(itemCardField.fieldContentView)
        }
        return ret
    }
}