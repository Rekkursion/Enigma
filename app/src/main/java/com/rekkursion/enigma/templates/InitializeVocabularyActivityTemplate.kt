package com.rekkursion.enigma.templates

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.rekkursion.dialogfloatingactionbutton.ListBottomSheetDialogFloatingActionButton
import com.rekkursion.enigma.R
import com.rekkursion.enigma.activities.EditVocabularyActivity
import com.rekkursion.enigma.activities.VocabularyActivity
import com.rekkursion.enigma.adapters.VocabularyViewPagerAdapter
import com.rekkursion.enigma.enums.CommandType
import com.rekkursion.enigma.managers.CommandManager
import com.rekkursion.enigma.managers.DataManager
import com.rekkursion.enigma.managers.PathManager
import com.rekkursion.enigma.models.FolderItem
import com.rekkursion.enigma.models.VocabularyItem
import com.rekkursion.enigma.utils.IndexConvertUtils
import kotlin.math.max

class InitializeVocabularyActivityTemplate(activity: AppCompatActivity):
    InitializeActivityTemplate(activity) {

    // the d-fab for editing or other operations of the current-showing vocabulary
    private lateinit var mDfabEdit: ListBottomSheetDialogFloatingActionButton

    // the view-pager-2 for showing a certain vocabulary
    private lateinit var mViewPager2: ViewPager2

    // the vocabulary list
    private val mVocabularyList = ArrayList<VocabularyItem>()

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

        // set the position of the selected child in the recycler-view
        mPositionInRecv = pos!!

        // get all vocabularies at the current path
        mVocabularyList.addAll(DataManager.getAllVocabularyItemsAtCertainPath(PathManager.getCurrentPath()))

        // update the view-pager-2
        updateViewPager2()
    }

    override fun initCommands() {

    }

    override fun initEvents() {
        // register the callbacks to the view-pager-2
        mViewPager2.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                mPositionInRecv = IndexConvertUtils.vocabularyOnly2BaseItem(
                    position,
                    mVocabularyList,
                    DataManager.getAllItemsAtCertainPath(PathManager.getCurrentPath())
                )
            }
        })

        // edit the current-showing vocabulary
        mDfabEdit.addItem(
            mActivity.getString(R.string.str_edit),
            View.OnClickListener {
                // create an intent to go to the edit-vocabulary-activity
                val theIntent = Intent(mActivity, EditVocabularyActivity::class.java)

                // put this vocabulary-item into a bundle
                val bundle = Bundle()
                bundle.putSerializable(VocabularyItem::class.java.name, mVocabularyList[mViewPager2.currentItem])
                bundle.putInt("position", IndexConvertUtils.vocabularyOnly2BaseItem(
                    mViewPager2.currentItem,
                    mVocabularyList,
                    DataManager.getAllItemsAtCertainPath(PathManager.getCurrentPath())
                ))

                // put the bundle as extras
                theIntent.putExtras(bundle)

                // start the activity
                mActivity.startActivityForResult(theIntent, VocabularyActivity.REQ_GO_TO_EDIT_VOCABULARY_ACTIVITY)
            }
        )

        // delete the current-showing vocabulary
        mDfabEdit.addItem(
            mActivity.getString(R.string.str_delete),
            View.OnClickListener {
                val item = mVocabularyList[mViewPager2.currentItem]
                AlertDialog.Builder(mActivity)
                    .setTitle(
                        mActivity.getString(R.string.str_attention_of_removing_item_dialog_title_prefix) +
                        mActivity.getString(R.string.str_vocabulary) +
                        " \"${item.getName()}\"" +
                        mActivity.getString(R.string.str_attention_of_removing_item_dialog_title_suffix)
                    )
                    .setMessage(mActivity.getString(R.string.str_attention_of_removing_item_dialog_message))
                    .setPositiveButton(mActivity.getString(R.string.str_ok)) { _, _ ->
                        // remove the item and notify that the data set has been changed
                        DataManager.removeItem(item)
                        // serialize all items
                        DataManager.saveAllItemsBySerialization(mActivity)

                        // update the position of the recv
                        mPositionInRecv = max(mPositionInRecv - 1, 0)

                        // update the view-pager-2 in the vocabulary-activity
                        updateViewPager2()
                        // update the recv in the item-list-fragment
                        CommandManager.doCommand(CommandType.ITEM_LIST_UPDATE)

                        // show the toast to let the user know the removing operation is succeed
                        Toast.makeText(
                            mActivity,
                            mActivity.getString(R.string.str_toast_remove_item_prefix) +
                                mActivity.getString(R.string.str_vocabulary) +
                                " \"${item.getName()}\"" +
                                mActivity.getString(R.string.str_toast_remove_item_suffix),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .setNegativeButton(mActivity.getString(R.string.str_cancel), null)
                    .create()
                    .show()
            }
        )
    }

    override fun doAfterInitialization() {

    }

    /* ================================================================== */

    // update hte view-pager-2's recycler-view
    internal fun updateViewPager2() {
        // update the vocabulary list
        mVocabularyList.clear()
        mVocabularyList.addAll(DataManager.getAllVocabularyItemsAtCertainPath(PathManager.getCurrentPath()))

        // if the vocabulary-list is empty, finish the activity directly
        if (mVocabularyList.isEmpty())
            mActivity.finish()
        // otherwise, generally update the view-pager-2
        else {
            // set the adapter of the view-pager-2
            mViewPager2.adapter = VocabularyViewPagerAdapter(mVocabularyList)

            // set the current item of the view-pager-2
            mViewPager2.setCurrentItem(IndexConvertUtils.baseItem2VocabularyOnly(
                mPositionInRecv,
                mVocabularyList,
                DataManager.getAllItemsAtCertainPath(PathManager.getCurrentPath())
            ), false)
        }
    }
}