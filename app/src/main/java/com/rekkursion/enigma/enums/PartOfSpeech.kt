package com.rekkursion.enigma.enums

enum class PartOfSpeech(val abbr: String, val chinese: String) {
    NOUN("n", "名詞"),
    COUNTABLE_NOUN("c", "可數名詞"),
    UNCOUNTABLE_NOUN("u", "不可數名詞"),
    COUNTABLE_UNCOUNTABLE_NOUN("c/u", "可數/不可數名詞"),
    VERB("v", "動詞"),
    TRANSITIVE_VERB("vt", "及物動詞"),
    INTRANSITIVE_VERB("vi", "不及物動詞"),
    TRANSITIVE_INTRANSITIVE_VERB("vt/vi", "及物/不及物動詞"),
    ADJECTIVE("a", "形容詞"),
    ADVERB("adv", "副詞"),
    PHRASE("phr", "片語"),
    PREPOSITION("prep", "介係詞"),
    CONJUNCTION("conj", "連接詞"),
    PRONOUN("pron", "代名詞"),
    ARTICLE("art", "冠詞"),
    INTERJECTION("int", "感嘆詞"),
    AUXILIARY_VERB("aux", "助動詞"),
    SINGLE("s", "單數"),
    PLURAL("pl", "複數"),
    ABBREVIATION("abbr", "縮寫");

    companion object {
        fun getChineseWithAbbrArray(): Array<String> = values().map { "${it.chinese} ${it.abbr}" }.toTypedArray()
    }
}
