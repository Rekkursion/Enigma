package com.rekkursion.enigma.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.R
import com.rekkursion.enigma.adapters.MeaningRecyclerViewAdapter
import com.rekkursion.enigma.itemdecorations.MeaningRecyclerViewItemDecoration
import com.rekkursion.enigma.models.VocabularyItem
import com.rekkursion.exactratingbar.ExactRatingBar
import com.rekkursion.pathview.PathView
import com.rekkursion.tagview.OnTagClickListener
import com.rekkursion.tagview.TagCloud
import com.rekkursion.tagview.TagView

class VocabularyDetailsView(context: Context, attrs: AttributeSet? = null): FrameLayout(context, attrs) {
    // the location
    private val mPathView: PathView

    // the english
    private val mTxtvEnglish: TextView

    // the proficiency
    private val mErbProficiency: ExactRatingBar

    // the meanings
    private val mRecvMeanings: RecyclerView

    // the remark
    private val mTxtvRemark: TextView

    // the tag-cloud
    private val mTagCloud: TagCloud

    /* =================================================================== */

    // primary constructor
    init {
        // inflate the layout
        LayoutInflater.from(context).inflate(R.layout.view_vocabulary_details, this)

        // get views
        mPathView = findViewById(R.id.path_view_at_vocabulary_details)
        mTxtvEnglish = findViewById(R.id.txtv_english_at_vocabulary_details)
        mErbProficiency = findViewById(R.id.erb_proficiency_at_vocabulary_details)
        mRecvMeanings = findViewById(R.id.recv_meaning_list_at_vocabulary_details)
        mTxtvRemark = findViewById(R.id.txtv_remark_at_vocabulary_details)
        mTagCloud = findViewById(R.id.tag_cloud_at_vocabulary_details)

        // set the layout-manager on the recycler-view
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = RecyclerView.VERTICAL
        mRecvMeanings.layoutManager = layoutManager

        // initialize attributes
        mRecvMeanings.addItemDecoration(MeaningRecyclerViewItemDecoration(context, 10))
        mTagCloud.possibleBackgroundColors = hashSetOf(TagView.DefaultBackgroundColor.YELLOW.color)

        // set events
        mTagCloud.setOnTagClickListener(object: OnTagClickListener {
            override fun onTagClick(tagCloud: TagCloud, tagView: TagView, index: Int) {
                // TODO: on-tag-click when showing details
            }
        })
    }

    /* =================================================================== */

    // set data by a certain vocabulary-item
    fun setItem(item: VocabularyItem) {
        mPathView.clear()
        mPathView.pushAll(item.pathNodesCopied)
        mTxtvEnglish.text = item.english
        mErbProficiency.currentValue = item.proficiency
        mRecvMeanings.adapter = MeaningRecyclerViewAdapter(item.meaningListCopied)
        mTxtvRemark.text = item.remark
        mTxtvRemark.visibility = if (item.remark.isEmpty()) View.GONE else View.VISIBLE
        item.tagListCopied.forEach { mTagCloud.addTag(it) }
    }
}