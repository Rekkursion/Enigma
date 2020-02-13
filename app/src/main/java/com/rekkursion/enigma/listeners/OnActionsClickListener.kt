package com.rekkursion.enigma.listeners

interface OnActionsClickListener {
    // the one which has been click is the go-up button
    fun onGoUpClickListener()

    // the one which has been click is the go-down button
    fun onGoDownClickListener()

    // the one which has been click is the close button
    fun onCloseClickListener()
}