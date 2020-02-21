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
import com.rekkursion.enigma.commands.itemlistcommand.*
import com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand.CertainItemCheckSummaryCommand
import com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand.CertainItemEnterFolderCommand
import com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand.CertainItemExpandOrUnexpandCommand
import com.rekkursion.enigma.commands.itemlistcommand.itemlistshowdialogcommand.ItemListShowDialogFolderItemCommand
import com.rekkursion.enigma.commands.itemlistcommand.itemlistshowdialogcommand.ItemListShowDialogVocabularyItemMasterCommand
import com.rekkursion.enigma.enums.CommandType
import com.rekkursion.enigma.enums.ItemType
import com.rekkursion.enigma.listeners.OnFragmentGoBackListener
import com.rekkursion.enigma.listeners.OnItemListRecyclerViewItemTouchListener
import com.rekkursion.enigma.managers.CommandManager
import com.rekkursion.enigma.managers.PathManager
import com.rekkursion.enigma.states.RecvStateContext
import com.rekkursion.enigma.templates.InitializeFragmentTemplate
import com.rekkursion.enigma.templates.InitializeItemListFragmentTemplate
import com.rekkursion.pathview.OnPathNodeClickListener
import com.rekkursion.pathview.PathView

class ItemListFragment: Fragment(), OnFragmentGoBackListener {
    // static scope
    companion object {
        // create a new instance of this fragment
        @JvmStatic
        fun newInstance() = ItemListFragment()

        // request codes
        internal const val REQ_GO_TO_NEW_ITEM_ACTIVITY_FOR_NEW_FOLDER = 4731
        internal const val REQ_GO_TO_NEW_ITEM_ACTIVITY_FOR_NEW_VOCABULARY = 5371
        internal const val REQ_GO_TO_EDIT_VOCABULARY_ACTIVITY = 8620
    }

    /* =================================================================== */

    // inflate the layout for this fragment
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_vocabulary_list, container, false)
    }

    // the root-view created, initialize views
    override fun onViewCreated(rootView: View, savedInstanceState: Bundle?) {
        super.onViewCreated(rootView, savedInstanceState)
        // initialize
        InitializeItemListFragmentTemplate(this, rootView).initialize()
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
            if (resultCode == Activity.RESULT_CANCELED)
                return

            // add new items
            CommandManager.doCommand(CommandType.ITEM_LIST_ADD_NEW_ITEMS)
        }

        // if back from edit-vocabulary-activity
        else if (requestCode == REQ_GO_TO_EDIT_VOCABULARY_ACTIVITY) {
            AlertDialog.Builder(context).setMessage("Good!!!!").show()
        }

        super.onActivityResult(requestCode, resultCode, data)
    }

    // is equivalent to activity's on-back-pressed
    override fun onGoBack(): Boolean {
        CommandManager.doCommand(CommandType.ITEM_LIST_BACK_TO_PREVIOUS_FOLDER)
        return true
    }
}
