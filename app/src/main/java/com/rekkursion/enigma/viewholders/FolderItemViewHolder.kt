package com.rekkursion.enigma.viewholders

import android.view.View
import android.widget.TextView
import com.rekkursion.enigma.R

class FolderItemViewHolder(itemView: View): BaseItemViewHolder(itemView) {
    // override the view-type as a folder-item
    override val type: Int = BaseItemViewType.FOLDER_ITEM.ordinal

    // the text-view of the folder name
    private val mTxtvFolderName: TextView = itemView.findViewById(R.id.txtv_folder_name_at_folder_recv_item)
    val txtvFolderName get() = mTxtvFolderName

    // the text-view of the numbers of folders and vocabularies
    private val mTxtvNumsOfFoldersAndVocabularies: TextView = itemView.findViewById(R.id.txtv_nums_of_folders_and_vocabularies_at_folder_recv_item)
    val txtvNumsOfFoldersAndVocabularies get() = mTxtvNumsOfFoldersAndVocabularies
}