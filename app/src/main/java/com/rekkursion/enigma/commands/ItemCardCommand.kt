package com.rekkursion.enigma.commands

import com.rekkursion.enigma.activities.NewItemActivity

interface ItemCardCommand: BaseCommand {
    // the new-item-activity
    val mNewItemActivityInstance: NewItemActivity
}