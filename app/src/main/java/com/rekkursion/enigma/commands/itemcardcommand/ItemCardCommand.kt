package com.rekkursion.enigma.commands.itemcardcommand

import com.rekkursion.enigma.activities.NewItemActivity
import com.rekkursion.enigma.commands.BaseCommand

abstract class ItemCardCommand(newItemActivity: NewItemActivity): BaseCommand {
    // the new-item-activity
    val mNewItemActivityInstance: NewItemActivity = newItemActivity
}