package com.rekkursion.enigma.activities

import android.view.View
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity: AppCompatActivity() {
    abstract fun initViews(rootView: View)

    abstract fun initAttributes()

    abstract fun initCommands()

    abstract fun initEvents()
}