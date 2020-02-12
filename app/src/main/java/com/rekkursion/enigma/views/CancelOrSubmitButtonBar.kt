package com.rekkursion.enigma.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.marginBottom
import com.rekkursion.enigma.R
import com.rekkursion.enigma.listeners.OnButtonBarClickListener

class CancelOrSubmitButtonBar(context: Context, attrs: AttributeSet?): LinearLayoutCompat(context, attrs) {
    // the cancel button
    private val mBtnCancel: Button

    // the submit button
    private val mBtnSubmit: Button

    // the on-button-bar-click-listener
    private var mOnButtonBarClickListener: OnButtonBarClickListener? = null

    /* =================================================================== */

    // primary constructor
    init {
        // inflate the layout
        LayoutInflater.from(context).inflate(R.layout.view_cancel_or_submit_button_bar, this)

        // get views (cancel & submit buttons)
        mBtnCancel = findViewById(R.id.btn_cancel)
        mBtnSubmit = findViewById(R.id.btn_submit)

        // set events
        mBtnCancel.setOnClickListener { mOnButtonBarClickListener?.onCancelClickListener() }
        mBtnSubmit.setOnClickListener { mOnButtonBarClickListener?.onSubmitClickListener() }
    }

    /* =================================================================== */

    // set the on-button-bar-click-listener
    fun setOnButtonBarClickListener(onButtonBarClickListener: OnButtonBarClickListener) {
        mOnButtonBarClickListener = onButtonBarClickListener
    }
}