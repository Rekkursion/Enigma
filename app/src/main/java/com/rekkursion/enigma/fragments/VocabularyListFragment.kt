package com.rekkursion.enigma.fragments

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.dialogfloatingactionbutton.ListBottomSheetDialogFloatingActionButton

import com.rekkursion.enigma.R
import com.rekkursion.enigma.activities.NewItemActivity
import com.rekkursion.enigma.managers.DataManager
import com.rekkursion.enigma.managers.PathManager
import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter
import com.rekkursion.enigma.enums.ItemType
import com.rekkursion.enigma.managers.NewItemManager

class VocabularyListFragment: Fragment() {
    // static scope
    companion object {
        // create a new instance of this fragment
        @JvmStatic
        fun newInstance() = VocabularyListFragment()

        // request codes
        private const val REQ_GO_TO_NEW_ITEM_ACTIVITY_FOR_NEW_FOLDER = 4731
        private const val REQ_GO_TO_NEW_ITEM_ACTIVITY_FOR_NEW_VOCABULARY = 5371
    }

    /* =================================================================== */

    // the recycler-view for showing the folders and/or vocabularies
    private lateinit var mRecvItemList: RecyclerView

    // d-fab to prompt up the list-bottom-sheet-dialog and let the user choose add folder or vocabulary
    private lateinit var mDfabAddFolderOrVocabulary: ListBottomSheetDialogFloatingActionButton

    // the adapter for the recycler-view
    private val mRecvAdapter = ItemRecyclerViewAdapter(PathManager.itemListForRecv)

    /* =================================================================== */

    // inflate the layout for this fragment
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_vocabulary_list, container, false)
    }

    // the root-view created, initialize views
    override fun onViewCreated(rootView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(rootView, savedInstanceState)
        init(rootView)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    // back from an activity's finishing started at this fragment
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // if back from new-item-activity
        if (requestCode == REQ_GO_TO_NEW_ITEM_ACTIVITY_FOR_NEW_FOLDER || requestCode == REQ_GO_TO_NEW_ITEM_ACTIVITY_FOR_NEW_VOCABULARY) {
            // but the result-code is canceled, return directly
            if (resultCode == Activity.RESULT_CANCELED) return

            // add all items and notify that the data set has been changed
            DataManager.addItems(NewItemManager.newItemList)
            changeRecvAdapter()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    /* =================================================================== */

    // initialize everything
    private fun init(rootView: View) {
        initViews(rootView)
        initAttributes()
        initEvents()

        DataManager.loadAllItemsByDeSerialization(context, false)
        changeRecvAdapter()
    }

    // initialize views
    private fun initViews(rootView: View) {
        mRecvItemList = rootView.findViewById(R.id.recv_item_list)
        mDfabAddFolderOrVocabulary = rootView.findViewById(R.id.dfab_add_folder_or_vocabulary)
    }

    // initialize attributes of views
    private fun initAttributes() {
        // set the layout-manager on the recycler-view
        val layoutManager = LinearLayoutManager(context!!)
        layoutManager.orientation = RecyclerView.VERTICAL
        mRecvItemList.layoutManager = layoutManager
    }

    // initialize events of views
    private fun initEvents() {
        // new folder
        mDfabAddFolderOrVocabulary.addItem(getString(R.string.str_new_folder), View.OnClickListener {
            val intent = Intent(this.context, NewItemActivity::class.java)
            intent.putExtra(ItemType::name.toString(), ItemType.FOLDER.name)
            startActivityForResult(intent, REQ_GO_TO_NEW_ITEM_ACTIVITY_FOR_NEW_FOLDER)
        })

        // new vocabulary
        mDfabAddFolderOrVocabulary.addItem(getString(R.string.str_new_vocabulary), View.OnClickListener {
            val intent = Intent(this.context, NewItemActivity::class.java)
            intent.putExtra(ItemType::name.toString(), ItemType.VOCABULARY.name)
            startActivityForResult(intent, REQ_GO_TO_NEW_ITEM_ACTIVITY_FOR_NEW_VOCABULARY)
        })

        // TODO: mRecvItemList.addOnItemTouchListener()
    }

    // change the recycler-view's adapter
    private fun changeRecvAdapter() {
        val adapter = ItemRecyclerViewAdapter(PathManager.itemListForRecv)
        mRecvItemList.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}
