package com.zykrave.anirumy.feature.profile.stats

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zykrave.anirumy.core.model.base.Localizable
import com.zykrave.anirumy.core.resources.R

enum class UserStatType : Localizable {
    OVERVIEW,
    GENRES,
    TAGS,
    STAFF,
    VOICE_ACTORS,
    STUDIOS;

    @Composable
    override fun localized() = when (this) {
        OVERVIEW -> stringResource(R.string.overview)
        GENRES -> stringResource(R.string.genres)
        TAGS -> stringResource(R.string.tags)
        STAFF -> stringResource(R.string.staff)
        VOICE_ACTORS -> stringResource(R.string.voice_actors)
        STUDIOS -> stringResource(R.string.studios)
    }
}