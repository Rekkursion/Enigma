package com.rekkursion.enigma.templates

import android.view.View
import androidx.fragment.app.Fragment

abstract class InitializeFragmentTemplate(fragment: Fragment, rootView: View): InitializeViewsTemplate() {
    // the fragment to be initialized
    protected val mFragment = fragment

    // the root view of all children in the fragment
    protected val mRootView = rootView
}