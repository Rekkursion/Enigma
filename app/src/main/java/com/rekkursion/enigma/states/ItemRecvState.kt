package com.rekkursion.enigma.states

interface ItemRecvState {
    // do on-click event
    fun doOnClick(context: ItemRecvContext, position: Int)

    // do on-long-click event
    fun doOnLongClick(context: ItemRecvContext, position: Int)
}