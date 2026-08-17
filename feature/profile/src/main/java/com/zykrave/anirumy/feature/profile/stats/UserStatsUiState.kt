package com.zykrave.anirumy.feature.profile.stats

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.model.stats.StatDistributionType
import com.zykrave.anirumy.core.model.stats.overview.OverviewStats
import com.zykrave.anirumy.core.network.fragment.GenreStat
import com.zykrave.anirumy.core.network.fragment.StaffStat
import com.zykrave.anirumy.core.network.fragment.StudioStat
import com.zykrave.anirumy.core.network.fragment.TagStat
import com.zykrave.anirumy.core.network.fragment.VoiceActorStat
import com.zykrave.anirumy.core.network.type.MediaType
import com.zykrave.anirumy.core.base.state.UiState

@Immutable
data class UserStatsUiState(
    val userId: Int,
    val type: UserStatType = UserStatType.OVERVIEW,
    val mediaType: MediaType = MediaType.ANIME,
    val scoreType: StatDistributionType = StatDistributionType.TITLES,
    val lengthType: StatDistributionType = StatDistributionType.TITLES,
    val releaseYearType: StatDistributionType = StatDistributionType.TITLES,
    val startYearType: StatDistributionType = StatDistributionType.TITLES,
    val animeOverview: OverviewStats? = null,
    val mangaOverview: OverviewStats? = null,
    val genresType: StatDistributionType = StatDistributionType.TITLES,
    val animeGenres: List<GenreStat>? = null,
    val mangaGenres: List<GenreStat>? = null,
    val tagsType: StatDistributionType = StatDistributionType.TITLES,
    val animeTags: List<TagStat>? = null,
    val mangaTags: List<TagStat>? = null,
    val staffType: StatDistributionType = StatDistributionType.TITLES,
    val animeStaff: List<StaffStat>? = null,
    val mangaStaff: List<StaffStat>? = null,
    val voiceActorsType: StatDistributionType = StatDistributionType.TITLES,
    val voiceActors: List<VoiceActorStat>? = null,
    val studiosType: StatDistributionType = StatDistributionType.TITLES,
    val studios: List<StudioStat>? = null,
    val fetchFromNetwork: Boolean = false,
    override val error: String? = null,
    override val isLoading: Boolean = true,
) : UiState() {
    override fun setError(value: String?) = copy(error = value)
    override fun setLoading(value: Boolean) = copy(isLoading = value)
}