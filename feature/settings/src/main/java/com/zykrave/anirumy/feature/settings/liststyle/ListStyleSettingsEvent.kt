package com.zykrave.anirumy.feature.settings.liststyle

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.model.ListStyle
import com.zykrave.anirumy.core.network.type.MediaListStatus

@Immutable
interface ListStyleSettingsEvent {
    fun setAnimeListStyle(status: MediaListStatus, value: ListStyle)
    fun setMangaListStyle(status: MediaListStatus, value: ListStyle)
}