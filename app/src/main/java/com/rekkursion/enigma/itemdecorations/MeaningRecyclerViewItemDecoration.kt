package com.rekkursion.enigma.itemdecorations

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.R

class MeaningRecyclerViewItemDecoration(context: Context, size: Int): RecyclerView.ItemDecoration() {
    // the context
    private val mContext = context

    // the size of the divider
    private val mSize = size

    // the paint
    private val mPaint = Paint()

    /* =================================================================== */

    // primary constructor
    init {
        mPaint.color = mContext.getColor(R.color.color_divider_meaning_recv)
    }

    /* =================================================================== */

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        outRect.set(0, 0, 0, mSize)
    }

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        parent.children.forEachIndexed { idx, child ->
            val params = child.layoutParams as RecyclerView.LayoutParams
            val top = child.bottom - params.bottomMargin - parent.paddingTop
            val bottom = top + mSize

            c.drawRect(Rect(left, top, right, bottom), mPaint)
            if (idx == 0)
                c.drawRect(Rect(left, child.top, right, child.top + mSize), mPaint)
        }
    }
}