package com.rekkursion.enigma.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.rekkursion.enigma.R

class ItemCardField private constructor(context: Context, attrs: AttributeSet? = null): LinearLayoutCompat(context, attrs) {
    // for building the item-card-field
    class Builder(context: Context, attrs: AttributeSet? = null) {
        private val mInstance = ItemCardField(context, attrs)

        // TODO
    }

    /* =================================================================== */

    // the text-view for showing the field name
    private val mTxtvFieldName: TextView

    // the container for placing the field content
    private val mFlyFieldContentContainer: FrameLayout

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