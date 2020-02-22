package com.rekkursion.enigma.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.rekkursion.enigma.R
import com.rekkursion.enigma.templates.InitializeNewItemActivityTemplate

@SuppressLint("SetTextI18n")
class NewItemActivity: AppCompatActivity() {
    companion object {
        // the max number of new items at a time
        internal const val MAX_NUMBER_OF_NEW_ITEMS = 99
    }

    /* ================================================================== */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_item)

        // initialize
        InitializeNewItemActivityTemplate(this).initialize()
    }
}
