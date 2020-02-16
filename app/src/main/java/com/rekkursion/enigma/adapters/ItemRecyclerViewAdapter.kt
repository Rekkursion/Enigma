package com.rekkursion.enigma.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.R
import com.rekkursion.enigma.models.BaseItem
import com.rekkursion.enigma.models.FolderItem
import com.rekkursion.enigma.models.VocabularyItem
import com.rekkursion.enigma.viewholders.BaseItemViewHolder
import com.rekkursion.enigma.viewholders.FolderItemViewHolder
import com.rekkursion.enigma.viewholders.VocabularyItemMasterViewHolder
import com.rekkursion.enigma.viewholders.VocabularyItemSlaveViewHolder

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
                VocabularyItemMasterViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.view_vocabulary_recv_item_master, parent, false)
                )

            // if it's a vocabulary-item-slave
            else ->
                VocabularyItemSlaveViewHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.view_vocabulary_recv_item_slave, parent, false)
                )
        }
    }

    // bind the data from the view w/ the view-holder
    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(vh: BaseItemViewHolder, position: Int) {
        val viewType = getItemViewType(position)
        val (baseItem, baseItemPosition) = getBaseItemAndItsTruePosition(position)

        when (viewType) {
            // if it's a folder-item
            BaseItemViewHolder.BaseItemViewType.FOLDER_ITEM.ordinal -> {
                baseItem as FolderItem; vh as FolderItemViewHolder
                vh.txtvFolderName.text = baseItem.folderName
                vh.txtvNumOfVocabularies.text = "(${baseItem.numOfVocabularies})"
            }

            // if it's a vocabulary-item
            BaseItemViewHolder.BaseItemViewType.VOCABULARY_ITEM_MASTER.ordinal -> {
                baseItem as VocabularyItem; vh as VocabularyItemMasterViewHolder
                vh.txtvEnglish.text = baseItem.english
                vh.erbProficiency.currentValue = baseItem.proficiency
            }

            // TODO: vocabulary-item-slave
            BaseItemViewHolder.BaseItemViewType.VOCABULARY_ITEM_SLAVE.ordinal -> {
                baseItem as VocabularyItem; vh as VocabularyItemSlaveViewHolder

                val meaningIdx = position - baseItemPosition - 1
                if (meaningIdx >= 0 && meaningIdx < baseItem.numOfMeanings) {
                    val meaning = baseItem.getMeaningAt(meaningIdx)
                    vh.txtvPartOfSpeech.text = meaning?.partOfSpeech?.abbr ?: "null"
                    vh.txtvChinese.text = meaning?.chinese ?: "null"
                }
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

    // return the base-item (folder-item or vocabulary-item) by the position
    fun getBaseItemAndItsTruePosition(position: Int): Pair<BaseItem, Int> {
        var pos = position
        var counter = 0
        for ((idx, it) in mBaseItemList.withIndex()) {
            if (it is VocabularyItem && it.isExpanded) {
                if (pos >= idx && pos <= idx + it.numOfMeanings)
                    return Pair(it, counter)

                pos -= it.numOfMeanings
                counter += it.numOfMeanings
            }

            if (idx == pos)
                return Pair(it, counter)

            ++counter
        }

        return Pair(mBaseItemList[0], 0)
    }
}