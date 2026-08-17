package com.zykrave.anirumy.core.model
import com.zykrave.anirumy.core.resources.R

import androidx.annotation.Keep
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zykrave.anirumy.core.model.base.Localizable

@Keep
enum class CurrentListType: Localizable {
    AIRING,
    BEHIND,
    ANIME,
    MANGA,
    NEXT_SEASON;

    @Composable
    override fun localized() = when (this) {
        AIRING -> stringResource(R.string.airing)
        BEHIND -> stringResource(R.string.anime_behind)
        ANIME -> stringResource(R.string.watching)
        MANGA -> stringResource(R.string.reading)
        NEXT_SEASON -> stringResource(R.string.next_season)
    }
}
