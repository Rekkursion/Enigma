package com.rekkursion.enigma.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import com.rekkursion.enigma.R
import com.rekkursion.enigma.templates.InitializeMainActivityTemplate
import com.rekkursion.enigma.utils.GoBackListenerUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        InitializeMainActivityTemplate(this).initialize()
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

    // override the on-back-pressed method to let fragments are able to handle the go-back event
    override fun onBackPressed() {
        if (!GoBackListenerUtils.handleGoBack(this))
            super.onBackPressed()
    }
}
