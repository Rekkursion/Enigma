package com.rekkursion.enigma.views

import android.app.AlertDialog
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.LinearLayoutCompat
import com.rekkursion.enigma.R
import com.rekkursion.enigma.enums.PartOfSpeech
import com.rekkursion.enigma.listeners.OnActionsClickListener

class MeaningSettingView(context: Context, attrs: AttributeSet? = null): LinearLayoutCompat(context, attrs) {
    // the actions bar
    private val mActionsBar: ActionsTitleImageButtonBar

    // the button for selecting the part of speech
    private val mBtnSelectPartOfSpeech: Button

    // the edit-text for the chinese meaning
    private val mEdtChinese: EditText

    // the edit-text for the notes
    private val mEdtNotes: EditText

    // the linear-layout for placing example sentences
    private val mSentencesContainer: LinearLayoutCompat

    // the button for adding new example sentence
    private val mBtnAddSentence: Button

    // the selected part of speech
    private var mSelectedPartOfSpeech = PartOfSpeech.NOUN

    /* ================================================================== */

    // primary constructor
    init {
        // inflate the layout
        LayoutInflater.from(context).inflate(R.layout.view_meaning_setting, this)

        // get views
        mActionsBar = findViewById(R.id.actions_title_bar_at_meaning_setting)
        mBtnSelectPartOfSpeech = findViewById(R.id.btn_select_part_of_speech_at_meaning_setting)
        mEdtChinese = findViewById(R.id.edt_chinese_at_meaning_setting)
        mEdtNotes = findViewById(R.id.edt_notes_at_meaning_setting)
        mSentencesContainer = findViewById(R.id.lly_sentences_container_at_meaning_setting)
        mBtnAddSentence = findViewById(R.id.btn_add_sentence_at_meaning_setting)

        // set events
        initEvents()
    }

    /* ================================================================== */

    // initialize events of views
    private fun initEvents() {
        // the event of selecting the part of speech
        mBtnSelectPartOfSpeech.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.str_select_a_part_of_speech))
                .setItems(PartOfSpeech.getChineseWithAbbrArray()) { _, index ->
                    mSelectedPartOfSpeech = PartOfSpeech.values()[index]
                    mBtnSelectPartOfSpeech.text = mSelectedPartOfSpeech.abbr
                }
                .create()
                .show()
        }

        // the event of adding a new example sentence
        mBtnAddSentence.setOnClickListener {
            // the edit-text to let the user to input the sentence at the dialog
            val edtNewSentenceAtDialog = EditText(context)
            edtNewSentenceAtDialog.isSingleLine = true
            edtNewSentenceAtDialog.hint = context.getString(R.string.str_enter_new_sentence_hint)

            // the prompted-up dialog
            AlertDialog.Builder(context)
                .setTitle(context.getString(R.string.str_new_example_sentence))
                .setView(edtNewSentenceAtDialog)
                .setPositiveButton(context.getString(R.string.str_submit)) { _, _ ->
                    val newSentenceView = SentenceView(context, edtNewSentenceAtDialog.text.toString())
                    mSentencesContainer.addView(newSentenceView)
                }
                .setNegativeButton(context.getString(R.string.str_cancel), null)
                .create()
                .show()
        }
    }

    /* ================================================================== */

    // set the listener of actions-title-bar
    fun setOnActionsClickListener(onActionsClickListener: OnActionsClickListener) {
        mActionsBar.setOnActionsClickListener(onActionsClickListener)
    }
}