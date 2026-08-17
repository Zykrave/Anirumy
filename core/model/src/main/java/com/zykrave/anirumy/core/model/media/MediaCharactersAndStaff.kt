package com.zykrave.anirumy.core.model.media

import com.zykrave.anirumy.core.network.MediaCharactersAndStaffQuery

data class MediaCharactersAndStaff(
    val characters: List<MediaCharactersAndStaffQuery.Edge>,
    val staff: List<MediaCharactersAndStaffQuery.Edge1>,
)
