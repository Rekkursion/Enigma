package com.rekkursion.enigma.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.rekkursion.enigma.R
import com.rekkursion.enigma.listeners.OnActionsClickListener

class ActionsTitleImageButtonBar(context: Context, attrs: AttributeSet? = null): LinearLayoutCompat(context, attrs) {
    // the text-view for showing the title
    private val mTxtvTitle: TextView

    // the go-up imgbtn
    private val mImgbtnGoUp: ImageButton

    // the go-down imgbtn
    private val mImgbtnGoDown: ImageButton

    // the close imgbtn
    private val mImgbtnClose: ImageButton

    // the listener
    private var mOnActionsClickListener: OnActionsClickListener? = null

    /* =================================================================== */

    // primary constructor
    init {
        // inflate the layout
        LayoutInflater.from(context).inflate(R.layout.view_actions_title_image_button_bar, this)

        // get views
        mTxtvTitle = findViewById(R.id.txtv_title_at_actions_title_image_button_bar)
        mImgbtnGoUp = findViewById(R.id.imgbtn_go_up)
        mImgbtnGoDown = findViewById(R.id.imgbtn_go_down)
        mImgbtnClose = findViewById(R.id.imgbtn_close)

        // set events
        mImgbtnGoUp.setOnClickListener { mOnActionsClickListener?.onGoUpClickListener() }
        mImgbtnGoDown.setOnClickListener { mOnActionsClickListener?.onGoDownClickListener() }
        mImgbtnClose.setOnClickListener { mOnActionsClickListener?.onCloseClickListener() }
    }

    /* =================================================================== */

    // set the listener
    fun setOnActionsClickListener(onActionsClickListener: OnActionsClickListener) {
        mOnActionsClickListener = onActionsClickListener
    }

    // set the enabilities of those two buttons
    fun setEnabilities(
        isImgbtnGoUpEnabled: Boolean = mImgbtnGoUp.isEnabled,
        isImgbtnGoDownEnabled: Boolean = mImgbtnGoDown.isEnabled,
        isImgbtnCloseEnabled: Boolean = mImgbtnClose.isEnabled
    ) {
        mImgbtnGoUp.isEnabled = isImgbtnGoUpEnabled
        mImgbtnGoDown.isEnabled = isImgbtnGoDownEnabled
        mImgbtnClose.isEnabled = isImgbtnCloseEnabled
    }

    // set the title
    fun setTitle(title: String) {
        mTxtvTitle.text = title
    }
}