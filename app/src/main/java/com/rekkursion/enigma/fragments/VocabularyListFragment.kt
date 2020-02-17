package com.rekkursion.enigma.fragments

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.dialogfloatingactionbutton.ListBottomSheetDialogFloatingActionButton

import com.rekkursion.enigma.R
import com.rekkursion.enigma.activities.NewItemActivity
import com.rekkursion.enigma.commands.itemlistcommand.ItemListAddNewItemsCommand
import com.rekkursion.enigma.commands.itemlistcommand.ItemListBackToPreviousFolderCommand
import com.rekkursion.enigma.commands.itemlistcommand.ItemListCommand
import com.rekkursion.enigma.commands.itemlistcommand.ItemListLoadAllItemsCommand
import com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand.CertainItemEnterFolderCommand
import com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand.CertainItemExpandOrUnexpandCommand
import com.rekkursion.enigma.enums.ItemType
import com.rekkursion.enigma.listeners.OnFragmentGoBackListener
import com.rekkursion.enigma.listeners.OnItemListRecyclerViewItemTouchListener
import com.rekkursion.enigma.managers.PathManager
import com.rekkursion.enigma.utils.GoBackListenerUtils
import com.rekkursion.enigma.viewholders.BaseItemViewHolder
import com.rekkursion.pathview.OnPathNodeClickListener
import com.rekkursion.pathview.PathView
import java.util.HashMap

class VocabularyListFragment: Fragment(), OnFragmentGoBackListener {
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

    // some commands for item list operations
    private val mCommands = HashMap<String, ItemListCommand>()

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
            // add new items
            mCommands[ItemListAddNewItemsCommand::class.java.name]?.execute()
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    // is equivalent to activity's on-back-pressed
    override fun onGoBack(): Boolean {
        mCommands[ItemListBackToPreviousFolderCommand::class.java.name]?.execute()
        return true
    }

    /* =================================================================== */

    // initialize everything
    private fun init(rootView: View) {
        initViews(rootView)
        initAttributes()
        initCommands()
        initEvents()

        // load all saved items by de-serialization
        mCommands[ItemListLoadAllItemsCommand::class.java.name]?.execute()
    }

    // initialize views
    private fun initViews(rootView: View) {
        // set the path view at the path-manager
        PathManager.setPathView(rootView.findViewById(R.id.path_view))
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

    // initialize commands
    private fun initCommands() {
        // command of loading all items by de-serialization
        mCommands[ItemListLoadAllItemsCommand::class.java.name] = ItemListLoadAllItemsCommand(mRecvItemList)
        // command of adding new items
        mCommands[ItemListAddNewItemsCommand::class.java.name] = ItemListAddNewItemsCommand(mRecvItemList)
        // command of going back to the previous folder
        mCommands[ItemListBackToPreviousFolderCommand::class.java.name] = ItemListBackToPreviousFolderCommand(mRecvItemList)
        // command of expanding or unexpanding a certain vocabulary-item
        mCommands[CertainItemExpandOrUnexpandCommand::class.java.name] = CertainItemExpandOrUnexpandCommand(mRecvItemList)
        // command of entering a certain folder-item
        mCommands[CertainItemEnterFolderCommand::class.java.name] = CertainItemEnterFolderCommand(mRecvItemList)
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

        // click-events of items of the recycler-view
        mRecvItemList.addOnItemTouchListener(OnItemListRecyclerViewItemTouchListener(
            mRecvItemList,
            object: OnItemListRecyclerViewItemTouchListener.OnItemListItemClickListener {
                override fun onItemClick(view: View?, position: Int) {
                    when (mRecvItemList.adapter?.getItemViewType(position)) {
                        // folder-item
                        BaseItemViewHolder.BaseItemViewType.FOLDER_ITEM.ordinal ->
                            (mCommands[CertainItemEnterFolderCommand::class.java.name] as? CertainItemEnterFolderCommand)?.executeAt(position)
                        // vocabulary-item-master
                        BaseItemViewHolder.BaseItemViewType.VOCABULARY_ITEM_MASTER.ordinal ->
                            (mCommands[CertainItemExpandOrUnexpandCommand::class.java.name] as? CertainItemExpandOrUnexpandCommand)?.executeAt(position)
                    }
                    // TODO: click on vocabulary-item-slave
                }

                override fun onItemLongClick(view: View?, position: Int) {
                    // TODO: long-click on folder- or vocabulary- item
                }
            }
        ))
    }
}
