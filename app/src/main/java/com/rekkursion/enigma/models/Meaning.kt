package com.rekkursion.enigma.models

import com.rekkursion.enigma.enums.PartOfSpeech
import java.io.Serializable

// the meaning of a vocabulary
class Meaning(
    partOfSpeech: PartOfSpeech,
    chinese: String,
    exampleSentences: ArrayList<String> = arrayListOf(),
    notes: String = ""
): Serializable {
    companion object {
        private const val serialVersionUID = 10615031L
    }

    /* =================================================================== */

    // the part of speech (cixing) of this meaning
    private var mPartOfSpeech = partOfSpeech
    var partOfSpeech get() = mPartOfSpeech; set(value) { mPartOfSpeech = value }

    // the chinese of this meaning
    private var mChinese = chinese
    var chinese get() = mChinese; set(value) { mChinese = value }

    // the example sentences of this meaning
    private val mExampleSentenceList = ArrayList(exampleSentences)
    val exampleSentenceListCopied get() = ArrayList(mExampleSentenceList)

    // the notes (beizhu) of this meaning
    private var mNotes = notes
    var notes get() = mNotes; set(value) { mNotes = value }
}