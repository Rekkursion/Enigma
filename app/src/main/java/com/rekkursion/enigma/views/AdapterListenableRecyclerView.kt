package com.rekkursion.enigma.views

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.RecyclerView

class AdapterListenableRecyclerView(context: Context, attrs: AttributeSet? = null): RecyclerView(context, attrs) {
    // the listener invoked when the adapter has been changed
    private var mOnAdapterChangeListener: OnAdapterChangeListener? = null

    /* =================================================================== */

    // set the on-adapter-change-listener
    fun setOnAdapterChangeListener(onAdapterChangeListener: OnAdapterChangeListener) {
        mOnAdapterChangeListener = onAdapterChangeListener
    }

    /* =================================================================== */

    // override the set-adapter method to insert the method of on-adapter-change-listener
    override fun setAdapter(adapter: Adapter<*>?) {
        mOnAdapterChangeListener?.onAdapterChange(this.adapter, adapter)
        super.setAdapter(adapter)
    }

    /* =================================================================== */

    // interface: be invoked when the adapter has been changed
    interface OnAdapterChangeListener {
        fun onAdapterChange(oldAdapter: Adapter<*>?, newAdapter: Adapter<*>?)
    }
}