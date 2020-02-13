package com.rekkursion.enigma.views.itemcard

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.widget.EditText
import com.rekkursion.enigma.R
import com.rekkursion.enigma.views.ItemCardField
import com.rekkursion.exactratingbar.ExactRatingBar
import com.rekkursion.exactratingbar.enums.ValueChangeScale

class VocabularyItemCard(context: Context, attrs: AttributeSet? = null): BaseItemCard(context, attrs) {
    // the edit-text of the english field
    private lateinit var mEdtEnglish: EditText

    // the exact-rating-bar of the proficiency field
    private lateinit var mErbProficiency: ExactRatingBar

    // primary constructor
    init {
        // initialize fields prior to the step of set
        initFields()
        // after the initialization of all fields, set them
        setFields()
    }

    /* =================================================================== */

    // initialize all fields of a vocabulary-item-card
    override fun initFields() {
        // the edit-text of the english field
        mEdtEnglish = EditText(context)
        mEdtEnglish.hint = context.getString(R.string.str_vocabulary_item_card_english_field_edit_text_hint)

        // the exact-rating-bar of the proficiency field
        mErbProficiency = ExactRatingBar(context)
        mErbProficiency.valueChangeScale = ValueChangeScale.HALF_STAR
        mErbProficiency.isIndicator = false
    }

    // set all fields of a folder-item-card
    override fun setFields() {
        // the edit-text of the english field
        val englishField = ItemCardField.Builder(context)
            .setFieldName(context.getString(R.string.str_vocabulary_item_card_english_field))
            .setContentView(mEdtEnglish)
            .create()
        mLlyFieldsContainer.addView(englishField)

        // the exact-rating-bar of the proficiency field
        val proficiencyField = ItemCardField.Builder(context)
            .setFieldName(context.getString(R.string.str_vocabulary_item_card_proficiency_field))
            .setContentView(mErbProficiency)
            .create()
        mLlyFieldsContainer.addView(proficiencyField)
    }
}