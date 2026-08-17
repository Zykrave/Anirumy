package com.zykrave.anirumy.core.model
import com.zykrave.anirumy.core.resources.R

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zykrave.anirumy.core.model.base.Localizable

enum class AppColorMode : Localizable {
    DEFAULT,
    PROFILE,
    CUSTOM;

    @Composable
    override fun localized() = stringResource(stringRes)

    val stringRes
        get() = when (this) {
            DEFAULT -> R.string.default_setting
            PROFILE -> R.string.profile
            CUSTOM -> R.string.custom_color
        }

    companion object {
        val entriesLocalized = entries.associateWith { it.stringRes }
    }
}
