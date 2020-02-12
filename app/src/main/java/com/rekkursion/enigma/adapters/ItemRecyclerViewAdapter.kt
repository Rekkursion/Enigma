package com.rekkursion.enigma.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.R
import com.rekkursion.enigma.enums.ItemType
import com.rekkursion.enigma.models.BaseItem
import com.rekkursion.enigma.models.FolderItem
import com.rekkursion.enigma.models.VocabularyItem
import com.rekkursion.enigma.viewholders.BaseItemViewHolder
import com.rekkursion.enigma.viewholders.FolderItemViewHolder
import com.rekkursion.enigma.viewholders.VocabularyItemViewHolder

class ItemRecyclerViewAdapter(items: ArrayList<BaseItem>): RecyclerView.Adapter<BaseItemViewHolder>() {
    // the list of all folders and/or vocabularies
    private val mBaseItemList = ArrayList<BaseItem>(items)

    /* =================================================================== */

    // add a new base-item (folder- or vocabulary- item)
    fun addBaseItem(baseItem: BaseItem, index: Int = mBaseItemList.size) {
        mBaseItemList.add(index, baseItem)
    }

    /* =================================================================== */

    // create a view-holder determined by the view-type
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseItemViewHolder {
        return when (viewType) {
            // if it's a folder-item
            ItemType.FOLDER.ordinal ->
                FolderItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.view_folder_recv_item, parent, false)
                )

            // if it's a vocabulary-item
            else ->
                VocabularyItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.view_vocabulary_recv_item, parent, false)
                )
        }
    }

    // bind the data from the view w/ the view-holder
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(vh: BaseItemViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        val baseItem = mBaseItemList[position]

        when (viewType) {
            // if it's a folder-item
            ItemType.FOLDER.ordinal -> {
                baseItem as FolderItem; vh as FolderItemViewHolder
                vh.txtvFolderName.text = baseItem.folderName
                vh.txtvNumOfVocabularies.text = "(${baseItem.numOfVocabularies})"
            }

            // if it's a vocabulary-item
            else -> {
                baseItem as VocabularyItem; vh as VocabularyItemViewHolder
                vh.txtvEnglish.text = baseItem.english
                vh.erbProficiency.currentValue = baseItem.proficiency
            }
        }
    }

    // return the count of items
    override fun getItemCount(): Int = mBaseItemList.size

    // return the view-type by the position
    override fun getItemViewType(position: Int): Int = mBaseItemList[position].type.ordinal
}