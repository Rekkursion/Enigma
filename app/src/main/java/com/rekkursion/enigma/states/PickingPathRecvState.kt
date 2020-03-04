package com.rekkursion.enigma.states

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.rekkursion.enigma.enums.CommandType
import com.rekkursion.enigma.itemdecorations.PickingPathItemListRecyclerViewItemDecoration
import com.rekkursion.enigma.managers.CommandManager

class PickingPathRecvState private constructor(): RecvState {
    override fun doOnClick(stateContext: RecvStateContext, position: Int) {
        // in picking-path state, the folder-item is the only one which can be clicked
        if (isFolderItem(stateContext, position)) {
            // go into this folder-item's content
            CommandManager.doCommand(CommandType.CERTAIN_ITEM_ENTER_FOLDER, position)
        }
    }

    override fun doOnLongClick(stateContext: RecvStateContext, position: Int) {
        // in picking-path state, no operations when long-clicked
    }

    override fun getItemDecoration(context: Context): RecyclerView.ItemDecoration =
        PickingPathItemListRecyclerViewItemDecoration(context)

    override fun toString(): String = "PICKING_PATH_STATE"

    /* =================================================================== */

    companion object {
        // the unique instance
        private val mInstance: PickingPathRecvState = PickingPathRecvState()
        // get the unique instance
        fun getInstance(): PickingPathRecvState = mInstance
    }
}