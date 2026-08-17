package com.zykrave.anirumy.core.model.media

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zykrave.anirumy.core.network.type.MediaType
import com.zykrave.anirumy.core.resources.R

@Composable
fun MediaType.localized() = when (this) {
    MediaType.ANIME -> stringResource(R.string.anime)
    MediaType.MANGA -> stringResource(R.string.manga)
    MediaType.UNKNOWN__ -> stringResource(R.string.unknown)
}