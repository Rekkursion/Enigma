package com.rekkursion.enigma.states

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class RecvStateContext(recyclerView: RecyclerView) {
    // the current state of the item recycler-view
    private var mState: RecvState = GeneralRecvState.getInstance()
    var state get() = mState; set(value) { mState = value }

    // the fragment of this state-context
    private var mFragment: Fragment? = null
    var fragment get() = mFragment; set(value) { mFragment = value }

    // the recycler-view of this context
    private val mRecv: RecyclerView = recyclerView
    val recyclerView get() = mRecv

    /* =================================================================== */

    // get the context (not this recv-state-context)
    fun getContext(): Context = recyclerView.context
}