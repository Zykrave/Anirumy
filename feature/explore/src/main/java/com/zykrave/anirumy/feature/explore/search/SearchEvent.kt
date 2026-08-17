package com.zykrave.anirumy.feature.explore.search

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.base.event.PagedEvent
import com.zykrave.anirumy.core.base.event.UiEvent
import com.zykrave.anirumy.core.model.SearchType
import com.zykrave.anirumy.core.model.genre.GenresAndTagsForSearch
import com.zykrave.anirumy.core.model.media.CountryOfOrigin
import com.zykrave.anirumy.core.model.media.MediaFormatLocalizable
import com.zykrave.anirumy.core.model.media.MediaSourceLocalizable
import com.zykrave.anirumy.core.model.media.MediaStatusLocalizable
import com.zykrave.anirumy.core.network.SearchMediaQuery
import com.zykrave.anirumy.core.network.fragment.BasicMediaListEntry
import com.zykrave.anirumy.core.network.type.MediaSeason
import com.zykrave.anirumy.core.network.type.MediaSort

@Immutable
interface SearchEvent : UiEvent, PagedEvent {
    fun setQuery(value: String)
    fun setSearchType(value: SearchType)
    fun setMediaSort(value: MediaSort)
    fun setMediaFormats(values: List<MediaFormatLocalizable>)
    fun setMediaStatuses(values: List<MediaStatusLocalizable>)
    fun setStartYear(value: Int?)
    fun setEndYear(value: Int?)
    fun setSeason(value: MediaSeason?)
    fun setEpCh(value: IntRange?)
    fun setDuration(value: IntRange?)
    fun setOnMyList(value: Boolean?)
    fun setIsDoujin(value: Boolean?)
    fun setIsAdult(value: Boolean?)
    fun setCountry(value: CountryOfOrigin?)
    fun setSources(values: List<MediaSourceLocalizable>)
    fun onGenreTagStateChanged(genresAndTagsForSearch: GenresAndTagsForSearch)
    fun clearFilters()
    fun selectMediaItem(value: SearchMediaQuery.Medium?)
    fun onUpdateListEntry(newListEntry: BasicMediaListEntry?)
}