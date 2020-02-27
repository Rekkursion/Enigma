package com.rekkursion.enigma.utils

import com.rekkursion.enigma.models.BaseItem
import com.rekkursion.enigma.models.VocabularyItem

object IndexConvertUtils {
    // convert the index of a vocabulary-item-only-list to the index of a base-item-list
    fun vocabularyOnly2BaseItem(
        idxAtVocabularyItemOnlyList: Int,
        vocabularyItemOnlyList: ArrayList<VocabularyItem>,
        baseItemList: ArrayList<BaseItem>
    ): Int = baseItemList.indexOf(vocabularyItemOnlyList[idxAtVocabularyItemOnlyList])

    // convert the index of a base-item-list to the index of a vocabulary-item-only-list
    fun baseItem2VocabularyOnly(
        idxAtBaseItemList: Int,
        vocabularyItemOnlyList: ArrayList<VocabularyItem>,
        baseItemList: ArrayList<BaseItem>
    ): Int = vocabularyItemOnlyList.indexOf(baseItemList[idxAtBaseItemList])
}