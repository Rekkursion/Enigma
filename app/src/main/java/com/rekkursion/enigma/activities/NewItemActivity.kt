package com.rekkursion.enigma.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.rekkursion.enigma.R
import com.rekkursion.enigma.enums.ItemType
import com.rekkursion.enigma.views.FolderItemCard

class NewItemActivity: AppCompatActivity() {
    // the container for placing item-cards
    private lateinit var mLlyCardsContainer: LinearLayoutCompat

    /* ================================================================== */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_item)

        initViews()
        initEvents()
    }

    /* ================================================================== */

    // initialize views
    private fun initViews() {
        mLlyCardsContainer = findViewById(R.id.lly_new_item_cards_container_at_new_item_activity)
    }

    // initialize events of views
    private fun initEvents() {
        if (intent.getStringExtra(ItemType::name.toString()) == ItemType.FOLDER.name)
            mLlyCardsContainer.addView(FolderItemCard(this))
        else {
            val txtv = TextView(this)
            txtv.text = "loser"
            mLlyCardsContainer.addView(txtv)
        }
    }
}
