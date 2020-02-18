package com.rekkursion.enigma.views.itemcard

import android.content.Context
import android.util.AttributeSet
import android.widget.EditText
import com.rekkursion.enigma.R
import com.rekkursion.enigma.views.ItemCardField
import com.rekkursion.enigma.views.MeaningSettingFieldContentView
import com.rekkursion.exactratingbar.ExactRatingBar
import com.rekkursion.exactratingbar.enums.ValueChangeScale
import com.rekkursion.tagview.TagCloud
import com.rekkursion.tagview.TagView

class VocabularyItemCard(context: Context, attrs: AttributeSet? = null): BaseItemCard(context, attrs) {
    // the edit-text of the english field
    private lateinit var mEdtEnglish: EditText

    // the meaning-setting-field-content-view of the meaning field
    private lateinit var mMeaningSettingFieldContentView: MeaningSettingFieldContentView

    // the exact-rating-bar of the proficiency field
    private lateinit var mErbProficiency: ExactRatingBar

    // the edit-text of the remark field
    private lateinit var mEdtRemark: EditText

    // the tag-cloud to let the user to add tags for this vocabulary
    private lateinit var mTagCloud: TagCloud

    /* =================================================================== */

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
        mEdtEnglish.isSingleLine = true
        mEdtEnglish.hint = context.getString(R.string.str_vocabulary_item_card_english_field_edit_text_hint)

        // the meaning-setting-field-content-view of the meaning field
        mMeaningSettingFieldContentView = MeaningSettingFieldContentView(context)

        // the exact-rating-bar of the proficiency field
        mErbProficiency = ExactRatingBar(context)
        mErbProficiency.valueChangeScale = ValueChangeScale.HALF_STAR
        mErbProficiency.isIndicator = false

        // the edit-text of the remark field
        mEdtRemark = EditText(context)
        mEdtRemark.hint = context.getString(R.string.str_vocabulary_item_card_remark_field_edit_text_hint)

        // the tag-cloud to let the user to add tags for this vocabulary
        mTagCloud = TagCloud(context)
        mTagCloud.possibleBackgroundColors = hashSetOf(TagView.DefaultBackgroundColor.YELLOW.color)
    }

    // set all fields of a folder-item-card
    override fun setFields() {
        // the path-view field
        val pathViewField = ItemCardField.Builder(context)
            .setFieldName(context.getString(R.string.str_base_item_card_path_field))
            .setContentView(mPathView)
            .create()
        mLlyFieldsContainer.addView(pathViewField)

        // the edit-text of the english field
        val englishField = ItemCardField.Builder(context)
            .setFieldName(context.getString(R.string.str_vocabulary_item_card_english_field))
            .setContentView(mEdtEnglish)
            .create()
        mLlyFieldsContainer.addView(englishField)

        // the meaning-setting-field-content-view of the meaning field
        val meaningsField = ItemCardField.Builder(context)
            .setFieldName(context.getString(R.string.str_vocabulary_item_card_meaning_field))
            .setContentView(mMeaningSettingFieldContentView)
            .create()
        mLlyFieldsContainer.addView(meaningsField)

        // the exact-rating-bar of the proficiency field
        val proficiencyField = ItemCardField.Builder(context)
            .setFieldName(context.getString(R.string.str_vocabulary_item_card_proficiency_field))
            .setContentView(mErbProficiency)
            .create()
        mLlyFieldsContainer.addView(proficiencyField)

        // the edit-text of the remark field
        val remarkField = ItemCardField.Builder(context)
            .setFieldName(context.getString(R.string.str_vocabulary_item_card_remark_field))
            .setContentView(mEdtRemark)
            .create()
        mLlyFieldsContainer.addView(remarkField)

        // the tag-cloud to let the user to add tags for this vocabulary
        val tagsField = ItemCardField.Builder(context)
            .setFieldName(context.getString(R.string.str_vocabulary_item_card_tags_field))
            .setContentView(mTagCloud)
            .create()
        mLlyFieldsContainer.addView(tagsField)
    }
}