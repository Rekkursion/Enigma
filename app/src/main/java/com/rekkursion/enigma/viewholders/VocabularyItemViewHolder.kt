package com.rekkursion.enigma.viewholders

import android.view.View
import android.widget.TextView
import com.rekkursion.enigma.R
import com.rekkursion.enigma.enums.ItemType
import com.rekkursion.exactratingbar.ExactRatingBar

class VocabularyItemViewHolder(itemView: View): BaseItemViewHolder(itemView) {
    // override the type as a vocabulary
    override val type: Int = ItemType.VOCABULARY.ordinal

    // the text-view of the english
    private val mTxtvEnglish: TextView = itemView.findViewById(R.id.txtv_english_at_vocabulary_recv_item)
    val txtvEnglish get() = mTxtvEnglish

    // the exact-rating-bar of the proficiency
    private val mErbProficiency: ExactRatingBar = itemView.findViewById(R.id.erb_proficiency_at_vocabulary_recv_item)
    val erbProficiency get() = mErbProficiency
}