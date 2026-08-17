package com.zykrave.anirumy.feature.explore.search.genretag

import com.zykrave.anirumy.core.resources.R
import com.zykrave.anirumy.core.ui.common.TabRowItem

enum class GenresTagsSheetTab {
    GENRES, TAGS;

    companion object {
        val tabRows = arrayOf(
            TabRowItem(value = GENRES, title = R.string.genres),
            TabRowItem(value = TAGS, title = R.string.tags),
        )
    }
}