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
import com.rekkursion.enigma.managers.PathManager
import com.rekkursion.enigma.models.BaseItem
import com.rekkursion.enigma.views.ActionsTitleImageButtonBar
import com.rekkursion.enigma.views.ItemCardField
import com.rekkursion.pathview.PathView

@Suppress("LeakingThis")
abstract class BaseItemCard(context: Context, attrs: AttributeSet?): FrameLayout(context, attrs) {
    // the path-view field
    protected lateinit var mPathView: PathView

    // the actions bar
    private val mActionsBar: ActionsTitleImageButtonBar

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

        // initialize the path-view in the item-card
        initPathViewInItemCard()
    }

    /* =================================================================== */

    // initialize the path-view in the item-card
    private fun initPathViewInItemCard() {
        // the path-view field
        mPathView = PathView(context)
        mPathView.isIndicator = true
        mPathView.separator = "\\"
        mPathView.viewHeight = 20
        mPathView.pushAll(PathManager.getAllPathNodes())
    }

    /* =================================================================== */

    // initialize all content views which are to be set
    protected abstract fun initContentViews()

    // set the data of all content views by a model of item
    protected abstract fun setDataOfContentViewsByItemModel(item: BaseItem? = null)

    // set the fields determined by the type of the item, i.e., folder or vocabulary
    protected abstract fun setFields()

    // create an item model
    abstract fun createItemModel(): BaseItem

    /* =================================================================== */

    // set the listener of actions-title-bar
    fun setOnActionsClickListener(onActionsClickListener: OnActionsClickListener) {
        mActionsBar.setOnActionsClickListener(onActionsClickListener)
    }

    // set the title of actions-title-bar
    fun setTitle(title: String) {
        mActionsBar.setTitle(title)
    }

    // show the actions-bar
    fun showActionsBar() {
        mActionsBar.visibility = View.VISIBLE
    }

    // hide the actions-bar
    fun hideActionsBar() {
        mActionsBar.visibility = View.GONE
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