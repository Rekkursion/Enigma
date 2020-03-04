package com.rekkursion.enigma.itemdecorations

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Rect
import android.util.Log
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.R
import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter
import com.rekkursion.enigma.viewholders.BaseItemViewHolder

class PickingPathItemListRecyclerViewItemDecoration(context: Context):
    GeneralItemListRecyclerViewItemDecoration(context) {

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight

        for (idx in 0 until parent.childCount) {
            val child = parent.getChildAt(idx)
            val position = parent.getChildAdapterPosition(child)
            val viewType = parent.adapter?.getItemViewType(position)

            if (viewType == BaseItemViewHolder.BaseItemViewType.VOCABULARY_ITEM_MASTER.ordinal) {
                mPaint.color = mContext.getColor(R.color.color_picking_path_vocabulary_item_master)
                c.drawRect(Rect(left, child.top, right, child.bottom), mPaint)
            }
        }
    }
}