package com.rekkursion.enigma.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.R
import com.rekkursion.enigma.models.Meaning
import com.rekkursion.enigma.views.SentenceView

class MeaningRecyclerViewAdapter(meanings: ArrayList<Meaning>): RecyclerView.Adapter<MeaningRecyclerViewAdapter.ViewHolder>() {
    // the list of all meanings
    private val mMeaningList = ArrayList<Meaning>(meanings)

    /* =================================================================== */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_meaning_recv, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meaning = mMeaningList[position]
        holder.setData(meaning)
    }

    override fun getItemCount(): Int = mMeaningList.size

    /* =================================================================== */

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val mTxtvPartOfSpeech: TextView = itemView.findViewById(R.id.txtv_part_of_speech_at_meaning_recv)
        private val mTxtvChinese: TextView = itemView.findViewById(R.id.txtv_chinese_at_meaning_recv)
        private val mTxtvNotes: TextView = itemView.findViewById(R.id.txtv_notes_at_meaning_recv)
        private val mLlySentencesContainer: LinearLayoutCompat = itemView.findViewById(R.id.lly_sentences_container_at_meaning_recv)
        private var mMeaning: Meaning? = null

        init {
            mTxtvPartOfSpeech.setOnClickListener {
                mMeaning?.let {
                    mTxtvPartOfSpeech.text = if (mTxtvPartOfSpeech.text.toString() == it.partOfSpeech.abbr)
                        it.partOfSpeech.chinese
                    else
                        it.partOfSpeech.abbr
                }
            }
        }

        fun setData(meaning: Meaning) {
            mMeaning = meaning
            mTxtvPartOfSpeech.text = meaning.partOfSpeech.abbr
            mTxtvChinese.text = meaning.chinese
            mTxtvNotes.text = meaning.notes
            mTxtvNotes.visibility = if (meaning.notes.trim().isEmpty()) View.GONE else View.VISIBLE
            mLlySentencesContainer.removeAllViews()
            meaning.exampleSentenceListCopied.forEach {
                mLlySentencesContainer.addView(SentenceView(itemView.context, it))
            }
        }
    }
}