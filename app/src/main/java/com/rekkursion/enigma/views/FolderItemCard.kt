package com.rekkursion.enigma.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import androidx.appcompat.widget.LinearLayoutCompat
import com.rekkursion.enigma.R

class FolderItemCard(context: Context, attrs: AttributeSet? = null): BaseItemCard(context, attrs) {
    // override the linear-layout for placing all fields
    override val mLlyFieldsContainer: LinearLayoutCompat

    // override the imgbtn for closing the item-card
    override val mImgbtnClose: ImageButton

    /* =================================================================== */

    // primary constructor
    init {
        // inflate the layout
        LayoutInflater.from(context).inflate(R.layout.view_folder_item_card, this)

        // get views
        mLlyFieldsContainer = findViewById(R.id.lly_fields_container)
        mImgbtnClose = findViewById(R.id.imgbtn_close_item_card)

        // set events
        mImgbtnClose.setOnClickListener { mOnItemCardCloseListener?.onItemCardClose() }

        // set fields
        setFields()
    }

    /* =================================================================== */

    // set all fields of a folder-item-card
    override fun setFields() {

    }
}