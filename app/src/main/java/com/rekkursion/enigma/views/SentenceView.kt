package com.rekkursion.enigma.views

import android.app.AlertDialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.rekkursion.enigma.R

class SentenceView(context: Context, attrs: AttributeSet? = null): LinearLayoutCompat(context, attrs) {
    // the text-view for showing the label
    private val mTxtvLabel: TextView

    // the text-view for showing the inputted example sentence
    private val mTxtvSentence: TextView

    /* =================================================================== */

    // primary constructor
    init {
        // inflate the layout
        LayoutInflater.from(context).inflate(R.layout.view_sentence, this)

        // get views
        mTxtvLabel = findViewById(R.id.txtv_label_at_sentence_view)
        mTxtvSentence = findViewById(R.id.txtv_sentence_at_sentence_view)

        // set events
        initEvents()
    }

    // second constructor
    constructor(context: Context, sentence: String): this(context, null) {
        mTxtvSentence.text = sentence
    }

    /* =================================================================== */

    // initialize the event
    private fun initEvents() {
        // set long-click event of this sentence-view
        super.setOnLongClickListener { doAfterLongClicked(); false }

        // set long-click events of all views and let them do the same thing
        mTxtvLabel.setOnLongClickListener { doAfterLongClicked(); false }
        mTxtvSentence.setOnLongClickListener { doAfterLongClicked(); false }
    }

    // the event of long clicking
    private fun doAfterLongClicked() {
        // TODO: set the long-click event of the sentence-view
        AlertDialog.Builder(context).setMessage("Long clicked at a sentence-view.").show()
    }

    /* =================================================================== */

    // override the set-on-long-click-listener and let it do nothing
    override fun setOnLongClickListener(l: OnLongClickListener?) {}
}