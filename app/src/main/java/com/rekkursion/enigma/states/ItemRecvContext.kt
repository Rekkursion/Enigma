package com.rekkursion.enigma.states

import android.content.Context
import androidx.recyclerview.widget.RecyclerView

class ItemRecvContext(recyclerView: RecyclerView) {
    // the current state of the item recycler-view
    private var mState: ItemRecvState = GeneralItemRecvState.getInstance()
    var state get() = mState; set(value) { mState = value }

    // the recycler-view of this context
    private val mRecv: RecyclerView = recyclerView
    val recyclerView get() = mRecv

    /* =================================================================== */

    // get the context (not this item-recv-context)
    fun getContext(): Context = recyclerView.context
}