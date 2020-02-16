package com.rekkursion.enigma.listeners

import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView

class OnItemListRecyclerViewItemTouchListener(recyclerView: RecyclerView, listener: OnItemListItemClickListener): RecyclerView.SimpleOnItemTouchListener() {
    private var mOnItemListItemClickListener: OnItemListItemClickListener? = listener
    private var mGestureDetector: GestureDetectorCompat? = null

    /* =================================================================== */

    // primary constructor
    init {
        mGestureDetector = GestureDetectorCompat(
            recyclerView.context,
            object: GestureDetector.SimpleOnGestureListener() {
                override fun onSingleTapUp(e: MotionEvent): Boolean {
                    val childView = recyclerView.findChildViewUnder(e.x, e.y)
                    if (childView != null && mOnItemListItemClickListener != null)
                        mOnItemListItemClickListener?.onItemClick(childView, recyclerView.getChildAdapterPosition(childView))
                    return true
                }

                override fun onLongPress(e: MotionEvent) {
                    val childView = recyclerView.findChildViewUnder(e.x, e.y)
                    if (childView != null && mOnItemListItemClickListener != null)
                        mOnItemListItemClickListener?.onItemLongClick(childView, recyclerView.getChildAdapterPosition(childView))
                }
            }
        )
    }

    /* =================================================================== */

    override fun onInterceptTouchEvent(rv: RecyclerView, e: MotionEvent): Boolean {
        mGestureDetector?.onTouchEvent(e)
        return false
    }

    /* =================================================================== */

    // interface
    interface OnItemListItemClickListener {
        fun onItemClick(view: View?, position: Int)
        fun onItemLongClick(view: View?, position: Int)
    }
}