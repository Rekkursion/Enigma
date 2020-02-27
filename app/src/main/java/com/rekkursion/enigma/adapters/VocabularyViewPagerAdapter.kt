package com.rekkursion.enigma.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.R
import com.rekkursion.enigma.models.VocabularyItem
import com.rekkursion.enigma.views.VocabularyDetailsView

class VocabularyViewPagerAdapter(vocabularyItems: ArrayList<VocabularyItem>): RecyclerView.Adapter<VocabularyViewPagerAdapter.ViewHolder>() {
    // the list of all to-be-shown vocabularies
    private val mVocabularyItemList = ArrayList<VocabularyItem>(vocabularyItems)

    /* =================================================================== */

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_vocabulary_view_pager, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mVocabularyItemList[position]
        holder.setItemContent(item)
    }

    override fun getItemCount(): Int = mVocabularyItemList.size

    /* =================================================================== */

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val mVocabularyDetailsView: VocabularyDetailsView = itemView.findViewById(R.id.vocabulary_details_view_at_vocabulary_view_pager_2)

        fun setItemContent(item: VocabularyItem) {
            mVocabularyDetailsView.setItem(item)
        }
    }
}