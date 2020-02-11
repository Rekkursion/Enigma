package com.rekkursion.enigma.utils.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract val type: Int
}