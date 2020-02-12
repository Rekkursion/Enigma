package com.rekkursion.enigma.views

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout

abstract class BaseItemCard(context: Context, attrs: AttributeSet?): FrameLayout(context, attrs) {
    // the button-bar w/ cancel & submit buttons
    protected abstract val mCancelOrSubmitButtonBar: CancelOrSubmitButtonBar
}