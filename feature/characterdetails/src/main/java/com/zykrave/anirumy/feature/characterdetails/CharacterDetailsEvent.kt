package com.zykrave.anirumy.feature.characterdetails

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.network.CharacterMediaQuery
import com.zykrave.anirumy.core.network.fragment.BasicMediaListEntry
import com.zykrave.anirumy.core.base.event.PagedEvent
import com.zykrave.anirumy.core.base.event.UiEvent

@Immutable
interface CharacterDetailsEvent : UiEvent, PagedEvent {
    fun toggleFavorite()
    fun selectMediaItem(value: CharacterMediaQuery.Edge?)
    fun onShowVoiceActorsSheet(item: CharacterMediaQuery.Edge)
    fun onUpdateListEntry(newListEntry: BasicMediaListEntry?)
}