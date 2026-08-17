package com.zykrave.anirumy.core.model
import com.zykrave.anirumy.core.resources.R

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zykrave.anirumy.core.model.base.Localizable

enum class HomeTab : Localizable {
    CURRENT,
    ACTIVITY_FEED;

    @Composable
    override fun localized() = stringResource(stringRes)

    @get:StringRes
    val stringRes
        get() = when (this) {
            ACTIVITY_FEED -> R.string.activity
            CURRENT -> R.string.current
        }

    companion object {
        val entriesLocalized = entries.associateWith { it.stringRes }

        fun valueOf(index: Int) = entries.find { it.ordinal == index }
    }
}
