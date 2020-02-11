package com.rekkursion.enigma.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.dialogfloatingactionbutton.ListBottomSheetDialogFloatingActionButton

import com.rekkursion.enigma.R
import com.rekkursion.enigma.managers.DataManager
import com.rekkursion.enigma.managers.PathManager
import com.rekkursion.enigma.utils.adapters.ItemRecyclerViewAdapter

class VocabularyListFragment: Fragment() {
    // static scope
    companion object {
        // create a new instance of this fragment
        @JvmStatic
        fun newInstance() = VocabularyListFragment()
    }

    /* =================================================================== */

    // the recycler-view for showing the folders and/or vocabularies
    private lateinit var mRecvItemList: RecyclerView

    // d-fab to prompt up the list-bottom-sheet-dialog and let the user choose add folder or vocabulary
    private lateinit var mDfabAddFolderOrVocabulary: ListBottomSheetDialogFloatingActionButton

    // the adapter for the recycler-view
    private val mRecvAdapter = ItemRecyclerViewAdapter(PathManager.currentBaseItems)

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

    /* =================================================================== */

    // initialize everything
    private fun init(rootView: View) {
        initViews(rootView)
        initAttributes()
        initEvents()

        DataManager.loadAllItemsBySerialization(context, true)
        mRecvItemList.adapter = mRecvAdapter
        PathManager.updateCurrentBaseItems()
        mRecvAdapter.notifyDataSetChanged()
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
            // TODO: add new folder
        })

        // new vocabulary
        mDfabAddFolderOrVocabulary.addItem(getString(R.string.str_new_vocabulary), View.OnClickListener {
            // TODO: add new vocabulary
        })

        //mRecvItemList.addOnItemTouchListener()
    }
}
