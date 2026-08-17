package com.zykrave.anirumy.core.model.media

import com.zykrave.anirumy.core.network.MediaRelationsAndRecommendationsQuery

data class MediaRelationsAndRecommendations(
    val relations: List<MediaRelationsAndRecommendationsQuery.Edge>,
    val recommendations: List<MediaRelationsAndRecommendationsQuery.Node>,
)
