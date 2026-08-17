package com.zykrave.anirumy.core.network.api.model

import com.zykrave.anirumy.core.network.type.MediaSeason
import kotlinx.serialization.Serializable

@Serializable
data class AnimeSeasonDto(
    val year: Int,
    val season: MediaSeason
)
