package com.rekkursion.enigma.enums

import com.rekkursion.enigma.commands.fragmentcommand.FragmentSwitchCommand
import com.rekkursion.enigma.commands.itemcardcommand.*
import com.rekkursion.enigma.commands.itemlistcommand.*
import com.rekkursion.enigma.commands.itemlistcommand.certainitemcommand.*
import com.rekkursion.enigma.commands.itemlistcommand.itemlistshowdialogcommand.ItemListShowDialogFolderItemCommand
import com.rekkursion.enigma.commands.itemlistcommand.itemlistshowdialogcommand.ItemListShowDialogVocabularyItemMasterCommand
import kotlin.reflect.KClass

enum class CommandType(val kClass: KClass<*>) {
    FRAGMENT_SWITCH(FragmentSwitchCommand::class),

    ITEM_CARD_ADD(ItemCardAddCommand::class),
    ITEM_CARD_CREATE_ITEMS_AND_STORE_THEM_IN_NEW_ITEM_MANAGER(ItemCardCreateItemsAndStoreThemInNewItemManagerCommand::class),
    ITEM_CARD_GO_UP_OR_GO_DOWN(ItemCardGoUpOrGoDownCommand::class),
    ITEM_CARD_REMOVE(ItemCardRemoveCommand::class),
    ITEM_CARD_VALIDATE(ItemCardValidateCommand::class),

    CERTAIN_ITEM_CHECK_SUMMARY(CertainItemCheckSummaryCommand::class),
    CERTAIN_ITEM_ALTER_VOCABULARY(CertainItemAlterVocabularyCommand::class),
    CERTAIN_ITEM_ENTER_FOLDER(CertainItemEnterFolderCommand::class),
    CERTAIN_ITEM_EXPAND_OR_UNEXPAND(CertainItemExpandOrUnexpandCommand::class),
    CERTAIN_ITEM_DELETE(CertainItemDeleteCommand::class),
    CERTAIN_ITEM_RENAME_FOLDER(CertainItemRenameFolderCommand::class),

    ITEM_LIST_SHOW_DIALOG_FOLDER_ITEM(ItemListShowDialogFolderItemCommand::class),
    ITEM_LIST_SHOW_DIALOG_VOCABULARY_ITEM_MASTER(ItemListShowDialogVocabularyItemMasterCommand::class),

    ITEM_LIST_ADD_NEW_ITEMS(ItemListAddNewItemsCommand::class),
    ITEM_LIST_BACK_TO_CERTAIN_FOLDER(ItemListBackToCertainFolderCommand::class),
    ITEM_LIST_BACK_TO_PREVIOUS_FOLDER(ItemListBackToPreviousFolderCommand::class),
    ITEM_LIST_LOAD_ALL_ITEMS(ItemListLoadAllItemsCommand::class),
    ITEM_LIST_UPDATE(ItemListUpdateCommand::class),
    ITEM_LIST_EXPAND_OR_UNEXPAND_ALL_VOCABULARIES(ItemListExpandOrUnexpandAllVocabulariesCommand::class),
    ITEM_LIST_MOVE_ITEM_COMMAND(ItemListMoveItemCommand::class)
}