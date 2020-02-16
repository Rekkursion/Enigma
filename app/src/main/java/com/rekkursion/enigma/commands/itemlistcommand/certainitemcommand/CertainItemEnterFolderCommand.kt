package com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand

import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.adapters.ItemRecyclerViewAdapter
import com.rekkursion.enigma.managers.PathManager
import com.rekkursion.enigma.models.FolderItem

class CertainItemEnterFolderCommand(recyclerView: RecyclerView): CertainItemCommand(recyclerView) {
    override fun executeAt(position: Int) {
        val baseItem = (mRecvItemList.adapter as? ItemRecyclerViewAdapter)?.getBaseItemAndItsTruePosition(position)?.first
        baseItem?.let {
            if (baseItem is FolderItem) {
                PathManager.pushPathNode(baseItem.folderName)
                changeAdapter()
            }
        }
    }
}