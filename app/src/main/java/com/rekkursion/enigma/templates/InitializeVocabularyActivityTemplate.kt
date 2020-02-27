package com.rekkursion.enigma.templates

import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.rekkursion.dialogfloatingactionbutton.ListBottomSheetDialogFloatingActionButton
import com.rekkursion.enigma.R
import com.rekkursion.enigma.adapters.VocabularyViewPagerAdapter
import com.rekkursion.enigma.managers.DataManager
import com.rekkursion.enigma.managers.PathManager
import com.rekkursion.enigma.models.VocabularyItem

class InitializeVocabularyActivityTemplate(activity: AppCompatActivity):
    InitializeActivityTemplate(activity) {

    // the d-fab for editing or other operations of the current-showing vocabulary
    private lateinit var mDfabEdit: ListBottomSheetDialogFloatingActionButton

    // the view-pager-2 for showing a certain vocabulary
    private lateinit var mViewPager2: ViewPager2

    // the item to be altered
    private lateinit var mItem: VocabularyItem

    // the position of the selected child-view in the recycler-view
    private var mPositionInRecv: Int = 0

    /* ================================================================== */

    override fun initViews() {
        mDfabEdit = mActivity.findViewById(R.id.dfab_edit_at_vocabulary_activity)
        mViewPager2 = mActivity.findViewById(R.id.view_pager_2_at_vocabulary_activity)
    }

    override fun initAttributes() {
        // get the vocabulary-item
        val item = mActivity.intent.extras
            ?.getSerializable(VocabularyItem::class.java.name) as? VocabularyItem

        // get the position of the selected child in the recycler-view
        val pos = mActivity.intent.extras?.getInt("position")

        // if failed, finish the activity directly
        if (item == null || pos == null) mActivity.finish()

        // set the item
        mItem = item!!

        // set the position of the selected child in the recycler-view
        mPositionInRecv = pos!!

        // get all vocabularies at the current path
        val vocabularyList = DataManager.getAllVocabularyItemsAtCertainPath(PathManager.getCurrentPath())
        // set the adapter of view-pager-2
        mViewPager2.adapter = VocabularyViewPagerAdapter(vocabularyList)
        // go to the page of the entering item
        mViewPager2.setCurrentItem(vocabularyList.indexOf(mItem), false)
    }

    override fun initCommands() {

    }

    override fun initEvents() {
        // register the callbacks to the view-pager-2
        mViewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {

            }
        })
    }

    override fun doAfterInitialization() {

    }
}