package com.zykrave.anirumy.feature.explore.search.genretag

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.model.genre.GenresAndTagsForSearch
import com.zykrave.anirumy.core.model.genre.SelectableGenre

@Immutable
interface GenresTagsEvent {
    fun onFilterChanged(value: String)
    fun onGenreUpdated(value: SelectableGenre)
    suspend fun onGenreRemoved(name: String): GenresAndTagsForSearch
    fun onTagUpdated(value: SelectableGenre)
    suspend fun onTagRemoved(name: String): GenresAndTagsForSearch
    fun onMinTagPercentageUpdated(value: Int)
    fun unselectAllGenresAndTags()
    fun resetData()
    suspend fun onDismissSheet()
    fun fetchGenreTagCollection()
}