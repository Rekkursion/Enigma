package com.rekkursion.enigma.templates

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.dialogfloatingactionbutton.ListBottomSheetDialogFloatingActionButton
import com.rekkursion.enigma.R
import com.rekkursion.enigma.activities.NewItemActivity
import com.rekkursion.enigma.commands.itemlistcommand.*
import com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand.*
import com.rekkursion.enigma.commands.itemlistcommand.itemlistshowdialogcommand.ItemListShowDialogFolderItemCommand
import com.rekkursion.enigma.commands.itemlistcommand.itemlistshowdialogcommand.ItemListShowDialogVocabularyItemMasterCommand
import com.rekkursion.enigma.enums.CommandType
import com.rekkursion.enigma.enums.ItemType
import com.rekkursion.enigma.fragments.ItemListFragment
import com.rekkursion.enigma.listeners.OnButtonBarClickListener
import com.rekkursion.enigma.listeners.OnItemListRecyclerViewItemTouchListener
import com.rekkursion.enigma.managers.CommandManager
import com.rekkursion.enigma.managers.PathManager
import com.rekkursion.enigma.states.GeneralRecvState
import com.rekkursion.enigma.states.PickingPathRecvState
import com.rekkursion.enigma.states.RecvStateContext
import com.rekkursion.enigma.views.AdapterListenableRecyclerView
import com.rekkursion.enigma.views.CancelOrSubmitButtonBar
import com.rekkursion.pathview.OnPathNodeClickListener
import com.rekkursion.pathview.PathView

class InitializeItemListFragmentTemplate(fragment: Fragment, rootView: View):
    InitializeFragmentTemplate(fragment, rootView),
    OnPathNodeClickListener,
    OnButtonBarClickListener {

    // the recycler-view for showing the folders and/or vocabularies
    private lateinit var mRecvItemList: AdapterListenableRecyclerView

    // d-fab to prompt up the list-bottom-sheet-dialog and let the user choose add folder or vocabulary
    private lateinit var mDfabAddFolderOrVocabulary: ListBottomSheetDialogFloatingActionButton

    // the path-view
    private lateinit var mPathView: PathView

    // the context of the item recycler-view
    private lateinit var mItemRecvStateContext: RecvStateContext

    // the text-view for hinting the user that there's still nothing in this folder
    private lateinit var mTxtvHintNothingInFolder: TextView

    // the linear-layout for placing the UIs when picking a path
    private lateinit var mLlyPickingPathUIsContainer: LinearLayoutCompat

    // the text-view for hinting the user to pick a path
    private lateinit var mTxtvHintPickingPath: TextView

    // the cancel-or-submit-button-bar
    private lateinit var mCancelOrSubmitButtonBar: CancelOrSubmitButtonBar

    /* =================================================================== */

    override fun onPathNodeClick(pathView: PathView, index: Int) {
        CommandManager.doCommand(CommandType.ITEM_LIST_BACK_TO_CERTAIN_FOLDER)
    }

    override fun onCancelClickListener() {
        // cancelled picking, turn back to general state
        mItemRecvStateContext.state = GeneralRecvState.getInstance()
    }

    override fun onSubmitClickListener() {
        // do the command of moving the selected item
        CommandManager.doCommand(CommandType.ITEM_LIST_MOVE_ITEM_COMMAND)
        // turn back to general state
        mItemRecvStateContext.state = GeneralRecvState.getInstance()
    }

    /* =================================================================== */

    override fun initViews() {
        mPathView = mRootView.findViewById(R.id.path_view)
        mRecvItemList = mRootView.findViewById(R.id.recv_item_list)
        mDfabAddFolderOrVocabulary = mRootView.findViewById(R.id.dfab_add_folder_or_vocabulary)
        mTxtvHintNothingInFolder = mRootView.findViewById(R.id.txtv_attention_of_nothing_in_folder)
        mLlyPickingPathUIsContainer = mRootView.findViewById(R.id.lly_picking_path_ui_container)
        mTxtvHintPickingPath = mRootView.findViewById(R.id.txtv_attention_of_picking_a_path)
        mCancelOrSubmitButtonBar = mRootView.findViewById(R.id.cancel_or_submit_button_bar_at_item_list_fragment_when_picking_path)
    }

    @SuppressLint("SetTextI18n")
    override fun initAttributes() {
        // set the path view at the path-manager
        PathManager.setPathView(mPathView)

        // set the layout-manager on the recycler-view
        val layoutManager = LinearLayoutManager(mFragment.context!!)
        layoutManager.orientation = RecyclerView.VERTICAL
        mRecvItemList.layoutManager = layoutManager

        // initialize the context of the item recycler-view
        mItemRecvStateContext = RecvStateContext(mRecvItemList)
        mItemRecvStateContext.fragment = mFragment

        // set the visibility of picking-path-ui-container to GONE
        mLlyPickingPathUIsContainer.visibility = View.GONE
        mLlyPickingPathUIsContainer.setBackgroundColor(Color.parseColor("#26345839"))

        // set the text of txtv-hint-picking-path
        mTxtvHintPickingPath.text = mFragment.getString(R.string.str_attention_of_picking_a_path_prefix) + mFragment.getString(R.string.str_submit) + mFragment.getString(R.string.str_attention_of_picking_a_path_suffix)
    }

    override fun initCommands() {
        // command of loading all items by de-serialization
        CommandManager.putCommand(CommandType.ITEM_LIST_LOAD_ALL_ITEMS, ItemListLoadAllItemsCommand(mRecvItemList))
        // command of adding new items
        CommandManager.putCommand(CommandType.ITEM_LIST_ADD_NEW_ITEMS, ItemListAddNewItemsCommand(mRecvItemList))
        // command of going back to the previous folder
        CommandManager.putCommand(CommandType.ITEM_LIST_BACK_TO_PREVIOUS_FOLDER, ItemListBackToPreviousFolderCommand(mRecvItemList))
        // command of going back to a certain folder
        CommandManager.putCommand(CommandType.ITEM_LIST_BACK_TO_CERTAIN_FOLDER, ItemListBackToCertainFolderCommand(mRecvItemList))
        // command of updating (changing) the adapter of the recv
        CommandManager.putCommand(CommandType.ITEM_LIST_UPDATE, ItemListUpdateCommand(mRecvItemList))
        // command of expanding or unexpanding all vocabulary-items
        CommandManager.putCommand(CommandType.ITEM_LIST_EXPAND_OR_UNEXPAND_ALL_VOCABULARIES, ItemListExpandOrUnexpandAllVocabulariesCommand(mRecvItemList))
        // command of expanding or unexpanding a certain vocabulary-item
        CommandManager.putCommand(CommandType.CERTAIN_ITEM_EXPAND_OR_UNEXPAND, CertainItemExpandOrUnexpandCommand(mRecvItemList))
        // command of entering a certain folder-item
        CommandManager.putCommand(CommandType.CERTAIN_ITEM_ENTER_FOLDER, CertainItemEnterFolderCommand(mRecvItemList))
        // command of checking summary of a certain item (folder- or vocabulary- item)
        CommandManager.putCommand(CommandType.CERTAIN_ITEM_CHECK_SUMMARY, CertainItemCheckSummaryCommand(mRecvItemList))
        // command of editing a certain item which is a vocabulary-item
        CommandManager.putCommand(CommandType.CERTAIN_ITEM_ALTER_VOCABULARY, CertainItemAlterVocabularyCommand(mRecvItemList))
        // command of deleting a certain item (folder- or vocabulary- item)
        CommandManager.putCommand(CommandType.CERTAIN_ITEM_DELETE, CertainItemDeleteCommand(mRecvItemList))
        // command of renaming a certain folder-item
        CommandManager.putCommand(CommandType.CERTAIN_ITEM_RENAME_FOLDER, CertainItemRenameFolderCommand(mRecvItemList))
        // command of showing dialog for a folder-item in the recycler-view
        CommandManager.putCommand(CommandType.ITEM_LIST_SHOW_DIALOG_FOLDER_ITEM, ItemListShowDialogFolderItemCommand(mRecvItemList))
        // command of showing dialog for a vocabulary-item-master in the recycler-view
        CommandManager.putCommand(CommandType.ITEM_LIST_SHOW_DIALOG_VOCABULARY_ITEM_MASTER, ItemListShowDialogVocabularyItemMasterCommand(mRecvItemList))
        // command of moving the selected item
        CommandManager.putCommand(CommandType.ITEM_LIST_MOVE_ITEM_COMMAND, ItemListMoveItemCommand(mRecvItemList))
    }

    override fun initEvents() {
        val context = mFragment.context!!

        // set the event of clicking on a certain path node
        mPathView.setOnPathNodeClickListener(this)

        // set the events of cancel & submit buttons
        mCancelOrSubmitButtonBar.setOnButtonBarClickListener(this)

        // new folder
        mDfabAddFolderOrVocabulary.addItem(context.getString(R.string.str_new_folder), View.OnClickListener {
            val intent = Intent(context, NewItemActivity::class.java)
            intent.putExtra(ItemType::name.toString(), ItemType.FOLDER.name)
            mFragment.startActivityForResult(intent, ItemListFragment.REQ_GO_TO_NEW_ITEM_ACTIVITY_FOR_NEW_FOLDER)
        })

        // new vocabulary
        mDfabAddFolderOrVocabulary.addItem(context.getString(R.string.str_new_vocabulary), View.OnClickListener {
            val intent = Intent(context, NewItemActivity::class.java)
            intent.putExtra(ItemType::name.toString(), ItemType.VOCABULARY.name)
            mFragment.startActivityForResult(intent, ItemListFragment.REQ_GO_TO_NEW_ITEM_ACTIVITY_FOR_NEW_VOCABULARY)
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

        // set the listener which is invoked when the adapter has been changed
        mRecvItemList.setOnAdapterChangeListener(object: AdapterListenableRecyclerView.OnAdapterChangeListener {
            override fun onAdapterChange(oldAdapter: RecyclerView.Adapter<*>?, newAdapter: RecyclerView.Adapter<*>?) {
                // set the visibility of the hint text-view
                mTxtvHintNothingInFolder.visibility =
                    if (newAdapter == null || newAdapter.itemCount == 0) View.VISIBLE
                    else View.GONE
            }
        })
    }

    override fun doAfterInitialization() {
        // load all saved items by de-serialization
        CommandManager.doCommand(CommandType.ITEM_LIST_LOAD_ALL_ITEMS)
    }

    /* =================================================================== */

    fun adjustViewsVisibilities() {
        when (mItemRecvStateContext.state) {
            is GeneralRecvState -> {
                mLlyPickingPathUIsContainer.visibility = View.GONE
                mDfabAddFolderOrVocabulary.visibility = View.VISIBLE
            }
            is PickingPathRecvState -> {
                mLlyPickingPathUIsContainer.visibility = View.VISIBLE
                mDfabAddFolderOrVocabulary.visibility = View.GONE
            }
        }
    }
}