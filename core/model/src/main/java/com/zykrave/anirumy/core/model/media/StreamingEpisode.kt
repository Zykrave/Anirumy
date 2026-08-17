package com.zykrave.anirumy.core.model.media

import com.zykrave.anirumy.core.network.MediaDetailsQuery

private val episodeNumberRegex = Regex("\\d+")

fun MediaDetailsQuery.StreamingEpisode.episodeNumber() =
    title?.let { episodeNumberRegex.find(it)?.value?.toIntOrNull() }