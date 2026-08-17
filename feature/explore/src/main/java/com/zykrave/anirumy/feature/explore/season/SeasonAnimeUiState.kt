package com.zykrave.anirumy.feature.explore.season

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.zykrave.anirumy.core.model.ListStyle
import com.zykrave.anirumy.core.model.media.AnimeSeason
import com.zykrave.anirumy.core.network.SeasonalAnimeQuery
import com.zykrave.anirumy.core.network.type.MediaSort
import com.zykrave.anirumy.core.base.state.PagedUiState

@Stable
data class SeasonAnimeUiState(
    val season: AnimeSeason? = null,
    val sort: MediaSort = MediaSort.POPULARITY_DESC,
    val animeSeasonal: SnapshotStateList<SeasonalAnimeQuery.Medium> = mutableStateListOf(),
    val selectedItem: SeasonalAnimeQuery.Medium? = null,
    val listStyle: ListStyle = ListStyle.GRID,
    val displayAdult: Boolean = false,
    override val page: Int = 1,
    override val hasNextPage: Boolean = true,
    override val isLoading: Boolean = true,
    override val error: String? = null,
) : PagedUiState() {
    override fun setError(value: String?) = copy(error = value)
    override fun setLoading(value: Boolean) = copy(isLoading = value)
    override fun setPage(value: Int) = copy(page = value)
    override fun setHasNextPage(value: Boolean) = copy(hasNextPage = value)

    val isAdult = displayAdult.takeIf { !it }
}
