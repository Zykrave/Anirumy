package com.zykrave.anirumy.feature.profile.stats

import androidx.compose.runtime.Stable
import com.zykrave.anirumy.core.base.event.UiEvent
import com.zykrave.anirumy.core.model.stats.StatDistributionType
import com.zykrave.anirumy.core.network.type.MediaType

@Stable
interface UserStatsEvent : UiEvent {
    fun setType(value: UserStatType)
    fun setMediaType(value: MediaType)
    fun setScoreType(value: StatDistributionType)
    fun setLengthType(value: StatDistributionType)
    fun setReleaseYearType(value: StatDistributionType)
    fun setStartYearType(value: StatDistributionType)
    fun setGenresType(value: StatDistributionType)
    fun setTagsType(value: StatDistributionType)
    fun setStaffType(value: StatDistributionType)
    fun setVoiceActorsType(value: StatDistributionType)
    fun setStudiosType(value: StatDistributionType)
    fun onRefresh()
}