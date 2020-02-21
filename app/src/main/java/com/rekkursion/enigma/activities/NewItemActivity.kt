package com.rekkursion.enigma.activities

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.core.view.children
import com.google.android.material.snackbar.Snackbar
import com.rekkursion.enigma.R
import com.rekkursion.enigma.commands.*
import com.rekkursion.enigma.commands.itemcardcommand.*
import com.rekkursion.enigma.listeners.OnButtonBarClickListener
import com.rekkursion.enigma.managers.CommandManager
import com.rekkursion.enigma.managers.NewItemManager
import com.rekkursion.enigma.templates.InitializeNewItemActivityTemplate
import com.rekkursion.enigma.views.CancelOrSubmitButtonBar
import java.util.HashMap

@SuppressLint("SetTextI18n")
class NewItemActivity: AppCompatActivity() {
    companion object {
        // the max number of new items at a time
        internal const val MAX_NUMBER_OF_NEW_ITEMS = 99
    }

    /* ================================================================== */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_item)

        // initialize
        InitializeNewItemActivityTemplate(this).initialize()
    }
}
