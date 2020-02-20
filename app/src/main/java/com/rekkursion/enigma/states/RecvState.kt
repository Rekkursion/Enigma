package com.rekkursion.enigma.states

interface RecvState {
    // do on-click event
    fun doOnClick(stateContext: RecvStateContext, position: Int)

    // do on-long-click event
    fun doOnLongClick(stateContext: RecvStateContext, position: Int)
}