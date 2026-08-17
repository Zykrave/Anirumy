package com.zykrave.anirumy.core.model.user

import com.zykrave.anirumy.core.network.type.UserStaffNameLanguage
import com.zykrave.anirumy.core.resources.R

fun UserStaffNameLanguage.stringRes() = when (this) {
    UserStaffNameLanguage.ROMAJI -> R.string.romaji
    UserStaffNameLanguage.ROMAJI_WESTERN -> R.string.romaji_western_order
    UserStaffNameLanguage.NATIVE -> R.string.native_title
    UserStaffNameLanguage.UNKNOWN__ -> R.string.unknown
}

val UserStaffNameLanguage.Companion.entriesLocalized
    get() = knownEntries.associateWith { it.stringRes() }