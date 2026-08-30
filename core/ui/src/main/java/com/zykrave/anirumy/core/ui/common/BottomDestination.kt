package com.zykrave.anirumy.core.ui.common

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.zykrave.anirumy.core.resources.R
import com.zykrave.anirumy.core.ui.common.navigation.Route

sealed class BottomDestination(
    val index: Int,
    val route: Route,
    @param:StringRes val title: Int,
    @param:DrawableRes val icon: Int,
    @param:DrawableRes val iconSelected: Int,
) {
    data object Home : BottomDestination(
        index = 0,
        route = Route.Home,
        title = R.string.home,
        icon = R.drawable.home_24,
        iconSelected = R.drawable.home_filled_24
    )

    data object AnimeList : BottomDestination(
        index = 1,
        route = Route.AnimeTab,
        title = R.string.anime,
        icon = R.drawable.live_tv_24,
        iconSelected = R.drawable.live_tv_filled_24
    )

    data object MangaList : BottomDestination(
        index = 2,
        route = Route.MangaTab,
        title = R.string.manga,
        icon = R.drawable.book_24,
        iconSelected = R.drawable.book_filled_24
    )

    data object Profile : BottomDestination(
        index = 3,
        route = Route.Profile,
        title = R.string.profile,
        icon = R.drawable.person_24,
        iconSelected = R.drawable.person_filled_24
    )

    data object Explore : BottomDestination(
        index = 4,
        route = Route.Explore,
        title = R.string.explore,
        icon = R.drawable.explore_24,
        iconSelected = R.drawable.explore_filled_24
    )

    data object Gallery : BottomDestination(
        index = 5,
        route = Route.Gallery,
        title = R.string.gallery,
        icon = R.drawable.nekoicon,
        iconSelected = R.drawable.nekoicon
    )

    @Composable
    fun Icon(selected: Boolean) {
        androidx.compose.material3.Icon(
            painter = painterResource(if (selected) iconSelected else icon),
            contentDescription = stringResource(title),
            modifier = androidx.compose.ui.Modifier.size(24.dp)
        )
    }

    companion object {
        val routes = setOf(Home.route, AnimeList.route, MangaList.route, Profile.route, Explore.route, Gallery.route)

        val values = listOf(Home, AnimeList, MangaList, Explore, Gallery, Profile)

        val railValues = listOf(Home, AnimeList, MangaList, Profile)

        fun Int.toBottomDestinationRoute(): Route? = values.find { it.index == this }?.route

        fun NavKey.isBottomDestination() = values.any { it.route == this }

        val BottomDestination.testTag
            get() = when (this) {
                is Home -> "HomeTab"
                is AnimeList -> "AnimeListTab"
                is MangaList -> "MangaListTab"
                is Profile -> "ProfileTab"
                is Explore -> "ExploreTab"
                is Gallery -> "GalleryTab"
            }
    }
}