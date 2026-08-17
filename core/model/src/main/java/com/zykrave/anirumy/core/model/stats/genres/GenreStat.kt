package com.zykrave.anirumy.core.model.stats.genres

import com.zykrave.anirumy.core.network.fragment.GenreStat
import com.zykrave.anirumy.core.network.type.MediaType
import com.zykrave.anirumy.core.model.stats.StatDistributionType

fun List<GenreStat>.sortedBy(
    type: StatDistributionType,
    mediaType: MediaType,
) = when (type) {
    StatDistributionType.TITLES -> sortedByDescending { it.count }

    StatDistributionType.TIME ->
        if (mediaType == MediaType.ANIME)
            sortedByDescending { it.minutesWatched }
        else
            sortedByDescending { it.chaptersRead }

    StatDistributionType.SCORE -> sortedByDescending { it.meanScore }
}