package com.zykrave.anirumy.core.model.user

import com.zykrave.anirumy.core.network.type.UserTitleLanguage
import com.zykrave.anirumy.core.resources.R

fun UserTitleLanguage.Companion.preferenceValues() = arrayOf(
    UserTitleLanguage.ROMAJI,
    UserTitleLanguage.ENGLISH,
    UserTitleLanguage.NATIVE
)

fun UserTitleLanguage.stringRes() = when (this) {
    UserTitleLanguage.ROMAJI, UserTitleLanguage.ROMAJI_STYLISED -> R.string.romaji
    UserTitleLanguage.ENGLISH, UserTitleLanguage.ENGLISH_STYLISED -> R.string.english
    UserTitleLanguage.NATIVE, UserTitleLanguage.NATIVE_STYLISED -> R.string.native_title
    UserTitleLanguage.UNKNOWN__ -> R.string.unknown
}

val UserTitleLanguage.Companion.entriesLocalized
    get() = preferenceValues().associateWith { it.stringRes() }