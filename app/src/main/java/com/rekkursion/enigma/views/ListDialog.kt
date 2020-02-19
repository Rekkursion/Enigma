package com.rekkursion.enigma.views

import android.app.AlertDialog
import android.content.Context
import android.view.View

class ListDialog(context: Context): AlertDialog(context) {
    // for building a list-dialog
    class Builder(context: Context): AlertDialog.Builder(context) {
        private val mItemList = ArrayList<Pair<String, View.OnClickListener?>>()

        // add the list's item
        fun addListItem(itemName: String, onClickListener: View.OnClickListener?): Builder {
            mItemList.add(Pair(itemName, onClickListener))
            return this
        }

        // override the create method to do set-items
        override fun create(): AlertDialog {
            super.setItems(mItemList.map { it.first }.toTypedArray()) { _, index ->
                mItemList.getOrNull(index)?.second?.onClick(null)
            }
            return super.create()
        }
    }
}