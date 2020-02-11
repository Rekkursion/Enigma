package com.rekkursion.enigma.models

import com.rekkursion.enigma.enums.PartOfSpeech

// the meaning of a vocabulary
class Meaning {
    // the part of speech (cixing) of this meaning
    private var mPartOfSpeech = PartOfSpeech.NOUN
    var partOfSpeech get() = mPartOfSpeech; set(value) { mPartOfSpeech = value }

    // the chinese of this meaning
    private var mChinese = ""
    var chinese get() = mChinese; set(value) { mChinese = value }

    // the example sentences of this meaning
    private var mExampleSentenceList = ArrayList<String>()
    val exampleSentenceListCopied get() = ArrayList(mExampleSentenceList)

    // the notes (beizhu) of this meaning
    private var mNotes = ""
    var notes get() = mNotes; set(value) { mNotes = value }
}