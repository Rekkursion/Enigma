package com.rekkursion.enigma.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.children
import com.rekkursion.enigma.R
import com.rekkursion.enigma.listeners.OnActionsClickListener

class MeaningSettingFieldContentView(context: Context, attrs: AttributeSet? = null): LinearLayoutCompat(context, attrs) {
    // the container of meaning-setting-views
    private val mLlyMeaningSettingViewsContainer: LinearLayoutCompat

    // the button for adding a new meaning-setting-view
    private val mBtnAddMeaningSettingView: Button

    /* =================================================================== */

    // primary constructor
    init {
        // inflate the layout
        LayoutInflater.from(context).inflate(R.layout.view_meaning_setting_field_content, this)

        // get views
        mLlyMeaningSettingViewsContainer = findViewById(R.id.lly_meaning_setting_views_container_at_meaning_setting_field_content_view)
        mBtnAddMeaningSettingView = findViewById(R.id.btn_add_new_meaning_setting_view_at_meaning_setting_field_content_view)

        // set events
        mBtnAddMeaningSettingView.setOnClickListener { addMeaningSettingView() }
    }

    /* =================================================================== */

    // add a new meaning-setting-view
    private fun addMeaningSettingView() {
        // create a new meaning-setting-view
        val meaningSettingView = MeaningSettingView(context)
        // set the actions of this new meaning-setting-view
        meaningSettingView.setOnActionsClickListener(object: OnActionsClickListener {
            override fun onGoUpClickListener() {
                val indexOfThisView = mLlyMeaningSettingViewsContainer.indexOfChild(meaningSettingView)
                if (indexOfThisView <= 0) return
                mLlyMeaningSettingViewsContainer.removeViewAt(indexOfThisView)
                mLlyMeaningSettingViewsContainer.addView(meaningSettingView, indexOfThisView - 1)
            }

            override fun onGoDownClickListener() {
                val indexOfThisView = mLlyMeaningSettingViewsContainer.indexOfChild(meaningSettingView)
                if (indexOfThisView < 0 || indexOfThisView >= mLlyMeaningSettingViewsContainer.children.toList().size - 1) return
                mLlyMeaningSettingViewsContainer.removeViewAt(indexOfThisView)
                mLlyMeaningSettingViewsContainer.addView(meaningSettingView, indexOfThisView + 1)
            }

            override fun onCloseClickListener() {
                val indexOfThisView = mLlyMeaningSettingViewsContainer.indexOfChild(meaningSettingView)
                if (indexOfThisView < 0) return
                mLlyMeaningSettingViewsContainer.removeViewAt(indexOfThisView)
            }
        })
        // add it into the container
        mLlyMeaningSettingViewsContainer.addView(meaningSettingView)
    }
}