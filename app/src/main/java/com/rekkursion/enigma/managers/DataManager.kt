package com.rekkursion.enigma.managers

import android.content.Context
import com.rekkursion.enigma.models.BaseItem
import com.rekkursion.enigma.utils.SerializationUtils
import java.io.File

object DataManager {
    // the destination file of base-item serialization
    private const val SERIALIZATION_ITEM_FILENAME: String = ".enigma_serialization_items"

    // the hash-map of base-items, including folder- and/or vocabulary- items
    private val mBaseItemHashMap: HashMap<String, ArrayList<BaseItem>> = hashMapOf()
    val baseItemHashMap get() = mBaseItemHashMap

    /* =================================================================== */

    // load all items by serialization
    fun loadAllItemsBySerialization(context: Context?, clearFirst: Boolean) {
        context?.let {
            if (clearFirst) mBaseItemHashMap.clear()
            val serialOutFile = File(context.filesDir, SERIALIZATION_ITEM_FILENAME)
            if (!serialOutFile.exists()) return
            val loaded = SerializationUtils.deSerialize<HashMap<String, ArrayList<BaseItem>>>(serialOutFile.path)
            mBaseItemHashMap.putAll(loaded ?: hashMapOf())

            PathManager.updateCurrentBaseItems()
        }
    }
}