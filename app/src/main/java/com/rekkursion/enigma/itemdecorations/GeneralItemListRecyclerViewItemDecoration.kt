package com.rekkursion.enigma.itemdecorations

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.R
import com.rekkursion.enigma.viewholders.BaseItemViewHolder

open class GeneralItemListRecyclerViewItemDecoration(context: Context): RecyclerView.ItemDecoration() {
    // the context
    protected val mContext = context

    // the paint
    protected val mPaint = Paint()

    /* =================================================================== */

    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (idx in 0 until parent.childCount) {
            val child = parent.getChildAt(idx)
            val position = parent.getChildAdapterPosition(child)
            val viewType = parent.adapter?.getItemViewType(position)

            mPaint.color = when (viewType) {
                BaseItemViewHolder.BaseItemViewType.FOLDER_ITEM.ordinal -> mContext.getColor(R.color.color_general_folder_item)
                BaseItemViewHolder.BaseItemViewType.VOCABULARY_ITEM_MASTER.ordinal -> mContext.getColor(R.color.color_general_vocabulary_item_master)
                else -> mContext.getColor(R.color.color_general_vocabulary_item_slave)
            }
            c.drawRect(Rect(left, child.top, right, child.bottom), mPaint)
        }
    }
}