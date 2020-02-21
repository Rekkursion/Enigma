package com.rekkursion.enigma.templates

import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rekkursion.enigma.R
import com.rekkursion.enigma.commands.fragmentcommand.FragmentSwitchCommand
import com.rekkursion.enigma.fragments.ItemListFragment
import com.rekkursion.enigma.managers.CommandManager

class InitializeMainActivityTemplate(activity: AppCompatActivity):
    InitializeActivityTemplate(activity),
    BottomNavigationView.OnNavigationItemSelectedListener {

    // b-nav to switch among all fragments
    private lateinit var mBnavMain: BottomNavigationView

    // the fragment of the item list
    private lateinit var mItemListFragment: Fragment

    // the fragment for testing
    private lateinit var mFragment: Fragment

    /* ================================================================== */

    // be invoked when an item of the bottom-navigation-view is selected
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // check which item has been selected
        return when (item.itemId) {
            // item list
            R.id.bnav_item_vocabulary_list -> {
                CommandManager.doCommand(FragmentSwitchCommand::class, R.id.lly_fragment_root_at_main, mItemListFragment)
                true
            }

            // practice
            R.id.bnav_item_practice -> {
                CommandManager.doCommand(FragmentSwitchCommand::class, R.id.lly_fragment_root_at_main, mFragment)
                // TODO: practice
                true
            }

            // more
            R.id.bnav_item_more -> {
                // TODO: more
                true
            }

            // error
            else -> false
        }
    }

    /* ================================================================== */

    override fun initViews() {
        mBnavMain = mActivity.findViewById(R.id.bnav_main)
        mItemListFragment = ItemListFragment.newInstance()
        mFragment = Fragment()
    }

    override fun initAttributes() {

    }

    override fun initCommands() {
        // command of switching among fragments in main-activity
        CommandManager.putCommand(FragmentSwitchCommand::class, FragmentSwitchCommand(mActivity.supportFragmentManager))
    }

    override fun initEvents() {
        // set the on-navigation-item-selected-listener for the bottom-navigation-view
        mBnavMain.setOnNavigationItemSelectedListener(this)
    }

    override fun doAfterInitialization() {
        // initially add the item-list-fragment
        CommandManager.doCommand(FragmentSwitchCommand::class, R.id.lly_fragment_root_at_main, mItemListFragment)

    }
}