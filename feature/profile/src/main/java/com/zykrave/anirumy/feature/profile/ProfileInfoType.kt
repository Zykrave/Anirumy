package com.zykrave.anirumy.feature.profile
import com.zykrave.anirumy.core.resources.R

import com.zykrave.anirumy.core.ui.common.TabRowItem

enum class ProfileInfoType {
    ABOUT, ACTIVITY, STATS, FAVORITES, SOCIAL;

    companion object {
        val tabRows = arrayOf(
            TabRowItem(ABOUT, icon = R.drawable.info_24),
            TabRowItem(ACTIVITY, icon = R.drawable.forum_24),
            TabRowItem(STATS, icon = R.drawable.bar_chart_24),
            TabRowItem(FAVORITES, icon = R.drawable.star_24),
            TabRowItem(SOCIAL, icon = R.drawable.group_24)
        )
    }
}
