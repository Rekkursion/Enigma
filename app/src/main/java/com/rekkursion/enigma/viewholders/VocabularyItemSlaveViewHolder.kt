package com.rekkursion.enigma.viewholders

import android.view.View
import android.widget.TextView
import com.rekkursion.enigma.R

class VocabularyItemSlaveViewHolder(itemView: View): BaseItemViewHolder(itemView) {
    override val type: Int = BaseItemViewType.VOCABULARY_ITEM_SLAVE.ordinal

    // the text-view of the part of speech
    private val mTxtvPartOfSpeech: TextView = itemView.findViewById(R.id.txtv_part_of_speech_at_vocabulary_recv_item_slave)
    val txtvPartOfSpeech get() = mTxtvPartOfSpeech

    // the text-view of the chinese
    private val mTxtvChinese: TextView = itemView.findViewById(R.id.txtv_chinese_at_vocabulary_recv_item_slave)
    val txtvChinese get() = mTxtvChinese
}