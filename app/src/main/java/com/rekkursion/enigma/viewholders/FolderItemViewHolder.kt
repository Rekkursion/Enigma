package com.rekkursion.enigma.viewholders

import android.view.View
import android.widget.TextView
import com.rekkursion.enigma.R
import com.rekkursion.enigma.enums.ItemType

class FolderItemViewHolder(itemView: View): BaseItemViewHolder(itemView) {
    // override the type as a folder
    override val type: Int = ItemType.FOLDER.ordinal

    // the text-view of the folder name
    private val mTxtvFolderName: TextView = itemView.findViewById(R.id.txtv_folder_name_at_folder_recv_item)
    val txtvFolderName get() = mTxtvFolderName

    // the text-view of the number of vocabularies
    private val mTxtvNumOfVocabularies: TextView = itemView.findViewById(R.id.txtv_num_of_vocabularies_at_folder_recv_item)
    val txtvNumOfVocabularies get() = mTxtvNumOfVocabularies
}