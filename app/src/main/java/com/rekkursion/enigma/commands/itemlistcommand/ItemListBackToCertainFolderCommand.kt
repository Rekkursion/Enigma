package com.rekkursion.enigma.commands.itemlistcommand

import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.managers.PathManager

class ItemListBackToCertainFolderCommand(recyclerView: RecyclerView): ItemListCommand(recyclerView) {
    /**
     * @param args: *no parameters*
     */
    override fun execute(vararg args: Any?) {
        PathManager.updateListForRecv()
        changeAdapter()
    }
}