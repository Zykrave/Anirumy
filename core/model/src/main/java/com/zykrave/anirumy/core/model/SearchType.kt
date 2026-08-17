package com.zykrave.anirumy.core.model
import com.zykrave.anirumy.core.resources.R

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zykrave.anirumy.core.model.base.Localizable

enum class SearchType : Localizable {
    ANIME {
        @Composable
        override fun localized() = stringResource(R.string.anime)
    },
    MANGA {
        @Composable
        override fun localized() = stringResource(R.string.manga)
    },
    CHARACTER {
        @Composable
        override fun localized() = stringResource(R.string.characters)
    },
    STAFF {
        @Composable
        override fun localized() = stringResource(R.string.staff)
    },
    STUDIO {
        @Composable
        override fun localized() = stringResource(R.string.studios)
    },
    USER {
        @Composable
        override fun localized() = stringResource(R.string.users)
    };

    val isSearchMedia get() = this == ANIME || this == MANGA
}
