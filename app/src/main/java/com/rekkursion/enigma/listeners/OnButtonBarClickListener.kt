package com.rekkursion.enigma.listeners

interface OnButtonBarClickListener {
    // the one which has been click is the cancel button
    fun onCancelClickListener()

    // the one which has been click is the submit button
    fun onSubmitClickListener()
}