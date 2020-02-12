package com.rekkursion.enigma.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.appcompat.widget.LinearLayoutCompat

abstract class BaseItemCard(context: Context, attrs: AttributeSet?): FrameLayout(context, attrs) {
    // the linear-layout for placing all fields
    protected abstract val mLlyFieldsContainer: LinearLayoutCompat

    // the button-bar w/ cancel & submit buttons
    protected abstract val mCancelOrSubmitButtonBar: CancelOrSubmitButtonBar
}