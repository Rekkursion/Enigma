package com.rekkursion.enigma.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rekkursion.enigma.R
import com.rekkursion.enigma.commands.fragmentcommand.FragmentSwitchCommand
import com.rekkursion.enigma.fragments.VocabularyListFragment
import com.rekkursion.enigma.managers.CommandManager
import com.rekkursion.enigma.utils.GoBackListenerUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    // b-nav to switch among all fragments
    private lateinit var mBnavMain: BottomNavigationView

    private lateinit var mVocabularyListFragment: Fragment
    private lateinit var mFragment: Fragment

    /* ================================================================== */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
        initCommands()
        initEvents()

        // initially add the vocabulary-list-fragment
        CommandManager.doCommand(FragmentSwitchCommand::class, R.id.lly_fragment_root_at_main, mVocabularyListFragment)
    }

    // inflate the menu at the bar
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // the selection event of menu items
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.item_action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    // the item selection event of the bottom-navigation-view
    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        // check which item has been selected
        return when (menuItem.itemId) {
            // vocabulary list
            R.id.bnav_item_vocabulary_list -> {
                CommandManager.doCommand(FragmentSwitchCommand::class, R.id.lly_fragment_root_at_main, mVocabularyListFragment)
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

    // override the on-back-pressed method to let fragments are able to handle the go-back event
    override fun onBackPressed() {
        if (!GoBackListenerUtils.handleGoBack(this))
            super.onBackPressed()
    }

    /* ================================================================== */

    // initialize views
    private fun initViews() {
        mBnavMain = findViewById(R.id.bnav_main)
        mVocabularyListFragment = VocabularyListFragment.newInstance()
        mFragment = Fragment()
    }

    private fun initCommands() {
        // command of switching among fragments in main-activity
        CommandManager.putCommand(
            FragmentSwitchCommand::class,
            FragmentSwitchCommand(supportFragmentManager)
        )
    }

    // initialize events of views
    private fun initEvents() {
        mBnavMain.setOnNavigationItemSelectedListener(this)
    }
}
