package com.rekkursion.enigma.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.appcompat.widget.LinearLayoutCompat
import com.rekkursion.enigma.R

class MeaningSettingView(context: Context, attrs: AttributeSet? = null): LinearLayoutCompat(context, attrs) {
    // TODO: members of meaning-setting-view

    /* ================================================================== */

    // primary constructor
    init {
        // inflate the layout
        LayoutInflater.from(context).inflate(R.layout.view_meaning_setting, this)
    }
}