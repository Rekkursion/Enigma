package com.rekkursion.enigma.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.children
import com.rekkursion.enigma.R

class ItemCardField private constructor(context: Context, attrs: AttributeSet? = null): LinearLayoutCompat(context, attrs) {
    // for building a new item-card-field
    class Builder(context: Context, attrs: AttributeSet? = null) {
        // the instance to be created
        private val mInstance = ItemCardField(context, attrs)

        // return the instance
        fun create(): ItemCardField = mInstance

        // set the field name
        fun setFieldName(fieldName: String): Builder {
            mInstance.mTxtvFieldName.text = fieldName
            return this
        }

        // set the content view
        fun setContentView(view: View): Builder {
            mInstance.mFlyFieldContentContainer.removeAllViews()
            mInstance.mFlyFieldContentContainer.addView(view)
            return this
        }
    }

    /* =================================================================== */

    // the text-view for showing the field name
    private val mTxtvFieldName: TextView
    val fieldName: String get() = mTxtvFieldName.text.toString()

    // the container for placing the field content
    private val mFlyFieldContentContainer: FrameLayout
    val fieldContentView: View? get() = mFlyFieldContentContainer.children.toList().getOrNull(0)

    /* =================================================================== */

    // primary constructor
    init {
        // inflate the layout
        LayoutInflater.from(context).inflate(R.layout.view_item_card_field, this)

        // get views
        mTxtvFieldName = findViewById(R.id.txtv_field_name)
        mFlyFieldContentContainer = findViewById(R.id.fly_field_content_container)
    }
}