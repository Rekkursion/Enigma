package com.rekkursion.enigma.templates

import androidx.appcompat.app.AppCompatActivity

abstract class InitializeActivityTemplate(activity: AppCompatActivity): InitializeTemplate() {
    // the activity to be initialized
    protected val mActivity = activity
}