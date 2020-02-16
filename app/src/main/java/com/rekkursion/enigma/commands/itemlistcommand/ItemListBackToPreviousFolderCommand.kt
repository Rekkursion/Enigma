package com.rekkursion.enigma.commands.itemlistcommand

import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.managers.PathManager

class ItemListBackToPreviousFolderCommand(recyclerView: RecyclerView): ItemListCommand(recyclerView) {
    /**
     * @param args: *no parameters*
     */
    override fun execute(vararg args: Any?) {
        if (PathManager.popPathNode() != null) {
            changeAdapter()
        }
    }
}