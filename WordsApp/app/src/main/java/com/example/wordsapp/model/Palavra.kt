package com.example.wordsapp.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
class Palavra(
    val partOfSpeech: String = "",
    val meanings: List<String> = emptyList(),
    val etymology: String = "",
    val error: String = ""
): Parcelable {
}