package com.zykrave.anirumy.feature.explore.discover

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.base.event.UiEvent
import com.zykrave.anirumy.core.network.fragment.BasicMediaDetails
import com.zykrave.anirumy.core.network.fragment.BasicMediaListEntry

@Immutable
interface DiscoverEvent : UiEvent {
    fun addNextInfo()
    fun fetchAiringAnime()
    fun fetchAiringAnimeOnMyList()
    fun fetchThisSeasonAnime()
    fun fetchTrendingAnime()
    fun fetchNextSeasonAnime()
    fun fetchTrendingManga()
    fun fetchNewlyAnime()
    fun fetchNewlyManga()
    fun refresh()
    fun selectItem(details: BasicMediaDetails?, listEntry: BasicMediaListEntry?)
}