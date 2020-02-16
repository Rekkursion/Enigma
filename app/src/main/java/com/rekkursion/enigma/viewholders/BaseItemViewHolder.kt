package com.rekkursion.enigma.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class BaseItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    // the view-types for showing on the recycler-view
    enum class BaseItemViewType {
        FOLDER_ITEM,
        VOCABULARY_ITEM_MASTER,
        VOCABULARY_ITEM_SLAVE,
    }

    /* =================================================================== */

    // the view-type of this base-item-view-holder
    abstract val type: Int
}