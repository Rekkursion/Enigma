package com.rekkursion.enigma.viewholders

import android.view.View
import android.widget.TextView
import com.rekkursion.enigma.R
import com.rekkursion.exactratingbar.ExactRatingBar

class VocabularyItemMasterViewHolder(itemView: View): BaseItemViewHolder(itemView) {
    // override the view-type as a vocabulary-item-master
    override val type: Int = BaseItemViewType.VOCABULARY_ITEM_MASTER.ordinal

    // the text-view of the english
    private val mTxtvEnglish: TextView = itemView.findViewById(R.id.txtv_english_at_vocabulary_recv_item_master)
    val txtvEnglish get() = mTxtvEnglish

    // the exact-rating-bar of the proficiency
    private val mErbProficiency: ExactRatingBar = itemView.findViewById(R.id.erb_proficiency_at_vocabulary_recv_item_master)
    val erbProficiency get() = mErbProficiency
}