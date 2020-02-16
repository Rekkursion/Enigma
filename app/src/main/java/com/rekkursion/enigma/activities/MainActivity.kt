package com.rekkursion.enigma.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rekkursion.enigma.R
import com.rekkursion.enigma.fragments.VocabularyListFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    // b-nav to switch among all fragments
    private lateinit var mBnavMain: BottomNavigationView

    /* ================================================================== */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        initViews()
        initEvents()
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
        // remove all fragments
        supportFragmentManager.fragments.forEach { supportFragmentManager.beginTransaction().remove(it).commit() }

        // check which item has been selected
        return when (menuItem.itemId) {
            // vocabulary list
            R.id.bnav_item_vocabulary_list -> {
                val f = VocabularyListFragment.newInstance()
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.lly_fragment_root_at_main, f)
                    .commit()
                true
            }

            // practice
            R.id.bnav_item_practice -> {
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

    // initialize views
    private fun initViews() {
        mBnavMain = findViewById(R.id.bnav_main)
    }

    // initialize events of views
    private fun initEvents() {
        mBnavMain.setOnNavigationItemSelectedListener(this)
    }
}
