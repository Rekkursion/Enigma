package com.rekkursion.enigma.commands.itemcardcommand

import android.content.Context
import androidx.appcompat.widget.LinearLayoutCompat
import com.rekkursion.enigma.commands.BaseCommand

abstract class ItemCardCommand(context: Context, cardContainer: LinearLayoutCompat): BaseCommand {
    // the context
    val mContext: Context = context

    // the container of item-cards
    val mCardContainer: LinearLayoutCompat = cardContainer
}