package com.rekkursion.enigma.commands.itemlistcommand

import androidx.recyclerview.widget.RecyclerView

class ItemListUpdateCommand(recyclerView: RecyclerView): ItemListCommand(recyclerView) {
    override fun execute(vararg args: Any?) {
        changeAdapter()
    }
}