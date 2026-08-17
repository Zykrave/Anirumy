package com.zykrave.anirumy.core.model
import com.zykrave.anirumy.core.resources.R

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zykrave.anirumy.core.model.base.Localizable
import kotlinx.serialization.Serializable

@Serializable
enum class FavoritesType : Localizable {
    ANIME,
    MANGA,
    CHARACTERS,
    STAFF,
    STUDIOS;

    @Composable
    override fun localized() = when (this) {
        ANIME -> stringResource(R.string.anime)
        MANGA -> stringResource(R.string.manga)
        CHARACTERS -> stringResource(R.string.characters)
        STAFF -> stringResource(R.string.staff)
        STUDIOS -> stringResource(R.string.studios)
    }
}
