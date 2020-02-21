package com.rekkursion.enigma.fragments

import android.app.Activity
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
import com.rekkursion.enigma.commands.itemlistcommand.*
import com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand.CertainItemCheckSummaryCommand
import com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand.CertainItemEnterFolderCommand
import com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand.CertainItemExpandOrUnexpandCommand
import com.rekkursion.enigma.commands.itemlistcommand.itemlistshowdialogcommand.ItemListShowDialogFolderItemCommand
import com.rekkursion.enigma.commands.itemlistcommand.itemlistshowdialogcommand.ItemListShowDialogVocabularyItemMasterCommand
import com.rekkursion.enigma.enums.ItemType
import com.rekkursion.enigma.listeners.OnFragmentGoBackListener
import com.rekkursion.enigma.listeners.OnItemListRecyclerViewItemTouchListener
import com.rekkursion.enigma.managers.CommandManager
import com.rekkursion.enigma.managers.PathManager
import com.rekkursion.enigma.states.RecvStateContext
import com.rekkursion.pathview.OnPathNodeClickListener
import com.rekkursion.pathview.PathView

class VocabularyListFragment: Fragment(), OnFragmentGoBackListener, OnPathNodeClickListener {
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

    // the path-view
    private lateinit var mPathView: PathView

    // the context of the item recycler-view
    private lateinit var mItemRecvStateContext: RecvStateContext

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
            CommandManager.doCommand(ItemListAddNewItemsCommand::class)
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    // is equivalent to activity's on-back-pressed
    override fun onGoBack(): Boolean {
        CommandManager.doCommand(ItemListBackToPreviousFolderCommand::class)
        return true
    }

    // click on a certain path node
    override fun onPathNodeClick(pathView: PathView, index: Int) {
        CommandManager.doCommand(ItemListBackToCertainFolderCommand::class)
    }

    /* =================================================================== */

    // initialize everything
    private fun init(rootView: View) {
        initViews(rootView)
        initAttributes()
        initCommands()
        initEvents()

        // load all saved items by de-serialization
        CommandManager.doCommand(ItemListLoadAllItemsCommand::class)
    }

    // initialize views
    private fun initViews(rootView: View) {
        mPathView = rootView.findViewById(R.id.path_view)
        mRecvItemList = rootView.findViewById(R.id.recv_item_list)
        mDfabAddFolderOrVocabulary = rootView.findViewById(R.id.dfab_add_folder_or_vocabulary)
    }

    // initialize attributes of views
    private fun initAttributes() {
        // set the path view at the path-manager
        PathManager.setPathView(mPathView)

        // set the layout-manager on the recycler-view
        val layoutManager = LinearLayoutManager(context!!)
        layoutManager.orientation = RecyclerView.VERTICAL
        mRecvItemList.layoutManager = layoutManager

        // initialize the context of the item recycler-view
        mItemRecvStateContext = RecvStateContext(mRecvItemList)
    }

    // initialize commands
    private fun initCommands() {
        // command of loading all items by de-serialization
        CommandManager.putCommand(ItemListLoadAllItemsCommand::class, ItemListLoadAllItemsCommand(mRecvItemList))
        // command of adding new items
        CommandManager.putCommand(ItemListAddNewItemsCommand::class, ItemListAddNewItemsCommand(mRecvItemList))
        // command of going back to the previous folder
        CommandManager.putCommand(ItemListBackToPreviousFolderCommand::class, ItemListBackToPreviousFolderCommand(mRecvItemList))
        // command of going back to a certain folder
        CommandManager.putCommand(ItemListBackToCertainFolderCommand::class, ItemListBackToCertainFolderCommand(mRecvItemList))
        // command of expanding or unexpanding a certain vocabulary-item
        CommandManager.putCommand(CertainItemExpandOrUnexpandCommand::class, CertainItemExpandOrUnexpandCommand(mRecvItemList))
        // command of entering a certain folder-item
        CommandManager.putCommand(CertainItemEnterFolderCommand::class, CertainItemEnterFolderCommand(mRecvItemList))
        // command of checking summary of a certain item (folder- or vocabulary- item)
        CommandManager.putCommand(CertainItemCheckSummaryCommand::class, CertainItemCheckSummaryCommand(mRecvItemList))
        // command of showing dialog for a folder-item in the recycler-view
        CommandManager.putCommand(ItemListShowDialogFolderItemCommand::class, ItemListShowDialogFolderItemCommand(mRecvItemList))
        // command of showing dialog for a vocabulary-item-master in the recycler-view
        CommandManager.putCommand(ItemListShowDialogVocabularyItemMasterCommand::class, ItemListShowDialogVocabularyItemMasterCommand(mRecvItemList))
    }

    // initialize events of views
    private fun initEvents() {
        // set the event of clicking on a certain path node
        mPathView.setOnPathNodeClickListener(this)

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
                    mItemRecvStateContext.state.doOnClick(mItemRecvStateContext, position)
                }
                override fun onItemLongClick(view: View?, position: Int) {
                    mItemRecvStateContext.state.doOnLongClick(mItemRecvStateContext, position)
                }
            }
        ))
    }
}
