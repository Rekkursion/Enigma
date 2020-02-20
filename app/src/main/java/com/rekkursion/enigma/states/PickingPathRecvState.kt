package com.rekkursion.enigma.states

import android.app.AlertDialog

class PickingPathRecvState private constructor(): RecvState {
    companion object {
        // the unique instance
        private val mInstance: PickingPathRecvState = PickingPathRecvState()
        // get the unique instance
        fun getInstance(): PickingPathRecvState = mInstance
    }

    /* =================================================================== */

    override fun doOnClick(stateContext: RecvStateContext, position: Int) {

    }

    override fun doOnLongClick(stateContext: RecvStateContext, position: Int) {
        val context = stateContext.getContext()
        AlertDialog.Builder(context).setMessage("picking").create().show()
        stateContext.state = GeneralRecvState.getInstance()
    }

    override fun toString(): String = "PICKING_PATH_STATE"
}