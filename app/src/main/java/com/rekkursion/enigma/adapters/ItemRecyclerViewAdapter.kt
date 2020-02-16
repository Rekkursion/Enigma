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
            BaseItemViewHolder.BaseItemViewType.FOLDER_ITEM.ordinal ->
                FolderItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.view_folder_recv_item, parent, false)
                )

            // if it's a vocabulary-item-master
            BaseItemViewHolder.BaseItemViewType.VOCABULARY_ITEM_MASTER.ordinal ->
                VocabularyItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.view_vocabulary_recv_item, parent, false)
                )

            // TODO: vocabulary-item-slave
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
        val baseItem = getBaseItem(position)

        when (viewType) {
            // if it's a folder-item
            BaseItemViewHolder.BaseItemViewType.FOLDER_ITEM.ordinal -> {
                baseItem as FolderItem; vh as FolderItemViewHolder
                vh.txtvFolderName.text = baseItem.folderName
                vh.txtvNumOfVocabularies.text = "(${baseItem.numOfVocabularies})"
            }

            // if it's a vocabulary-item
            BaseItemViewHolder.BaseItemViewType.VOCABULARY_ITEM_MASTER.ordinal -> {
                baseItem as VocabularyItem; vh as VocabularyItemViewHolder
                vh.txtvEnglish.text = baseItem.english
                vh.erbProficiency.currentValue = baseItem.proficiency
            }

            // TODO: vocabulary-item-slave
            BaseItemViewHolder.BaseItemViewType.VOCABULARY_ITEM_SLAVE.ordinal -> {
                baseItem as VocabularyItem; vh as VocabularyItemViewHolder
                vh.txtvEnglish.text = "good"
                vh.erbProficiency.currentValue = 0F
            }
        }
    }

    // return the count of items
    override fun getItemCount(): Int = mBaseItemList
        .map {
            if (it is FolderItem) 1
            else { if ((it as VocabularyItem).isExpanded) 1 + it.numOfMeanings else 1 }
        }
        .sum()

    // return the view-type by the position
    override fun getItemViewType(position: Int): Int {
        var counter = 0
        for (it in mBaseItemList) {
            if (counter == position) {
                return if (it is FolderItem)
                    BaseItemViewHolder.BaseItemViewType.FOLDER_ITEM.ordinal
                else
                    BaseItemViewHolder.BaseItemViewType.VOCABULARY_ITEM_MASTER.ordinal
            }

            ++counter
            if (it is VocabularyItem && it.isExpanded) {
                if (position >= counter && position < counter + it.numOfMeanings)
                    return BaseItemViewHolder.BaseItemViewType.VOCABULARY_ITEM_SLAVE.ordinal

                counter += it.numOfMeanings
            }
        }

        return BaseItemViewHolder.BaseItemViewType.VOCABULARY_ITEM_MASTER.ordinal
    }

    fun getBaseItem(position: Int): BaseItem {
        var counter = position
        for ((idx, it) in mBaseItemList.withIndex()) {
            if (it is VocabularyItem && it.isExpanded) {
                if (counter >= idx && counter <= idx + it.numOfMeanings)
                    return it

                counter -= it.numOfMeanings
            }

            if (idx == counter)
                return it
        }

        return mBaseItemList[0]
    }
}