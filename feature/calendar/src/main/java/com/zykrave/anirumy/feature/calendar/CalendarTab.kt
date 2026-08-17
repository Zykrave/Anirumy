package com.zykrave.anirumy.feature.calendar
import com.zykrave.anirumy.core.resources.R

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.zykrave.anirumy.core.model.base.Localizable
import com.zykrave.anirumy.core.ui.common.TabRowItem

enum class CalendarTab : Localizable {
    MONDAY,
    TUESDAY,
    WEDNESDAY,
    THURSDAY,
    FRIDAY,
    SATURDAY,
    SUNDAY;

    @get:StringRes
    val stringRes
        get() = when (this) {
            MONDAY -> R.string.monday
            TUESDAY -> R.string.tuesday
            WEDNESDAY -> R.string.wednesday
            THURSDAY -> R.string.thursday
            FRIDAY -> R.string.friday
            SATURDAY -> R.string.saturday
            SUNDAY -> R.string.sunday
        }

    @Composable
    override fun localized() = stringResource(stringRes)

    companion object {
        val tabRows = entries.map { TabRowItem(value = it, title = it.stringRes) }.toTypedArray()
    }
}
