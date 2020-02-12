package com.rekkursion.enigma.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    abstract val type: Int
}