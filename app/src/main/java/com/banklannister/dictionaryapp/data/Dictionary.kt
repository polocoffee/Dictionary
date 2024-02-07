package com.banklannister.dictionaryapp.data

data class Dictionary(
    val word: String,
    val phonetic: String?,
    val meanings: List<Meaning>,
)

data class Meaning(
    val partOfSpeech: String,
    val definitions: List<Definition>,
    val synonyms: List<String>,
    val antonyms: List<String>,
)

data class Definition(
    val definition: String
)