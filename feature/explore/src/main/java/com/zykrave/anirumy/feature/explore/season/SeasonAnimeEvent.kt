package com.zykrave.anirumy.feature.explore.season

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.model.ListStyle
import com.zykrave.anirumy.core.model.media.AnimeSeason
import com.zykrave.anirumy.core.network.SeasonalAnimeQuery
import com.zykrave.anirumy.core.network.fragment.BasicMediaListEntry
import com.zykrave.anirumy.core.network.type.MediaSort
import com.zykrave.anirumy.core.base.event.PagedEvent
import com.zykrave.anirumy.core.base.event.UiEvent

@Immutable
interface SeasonAnimeEvent : UiEvent, PagedEvent {
    fun setSeason(value: AnimeSeason)
    fun onChangeSort(value: MediaSort)
    fun selectItem(value: SeasonalAnimeQuery.Medium?)
    fun onUpdateListEntry(newListEntry: BasicMediaListEntry?)
    fun onChangeListStyle(value: ListStyle)
}