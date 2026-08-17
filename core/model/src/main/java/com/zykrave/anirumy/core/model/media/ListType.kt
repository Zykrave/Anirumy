package com.zykrave.anirumy.core.model.media

import com.zykrave.anirumy.core.network.type.MediaListStatus
import com.zykrave.anirumy.core.network.type.MediaType

data class ListType(
    val status: MediaListStatus,
    val mediaType: MediaType,
)
