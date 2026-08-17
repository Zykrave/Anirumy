package com.zykrave.anirumy.ui.screens.main

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.zykrave.anirumy.core.common.utils.ContextUtils.openActionView
import com.zykrave.anirumy.core.model.DeepLink
import com.zykrave.anirumy.core.model.HomeTab
import com.zykrave.anirumy.core.network.type.MediaType
import com.zykrave.anirumy.core.ui.common.LocalMarkdownUriHandler
import com.zykrave.anirumy.core.ui.common.LocalNavActionManager
import com.zykrave.anirumy.core.ui.common.navigation.Navigator
import com.zykrave.anirumy.core.ui.common.navigation.Route
import com.zykrave.anirumy.core.ui.composables.FullScreenImageView
import com.zykrave.anirumy.core.ui.composables.markdown.MarkdownUriHandler
import com.zykrave.anirumy.core.ui.composables.markdown.SpoilerSheet
import com.zykrave.anirumy.feature.activitydetails.ActivityDetailsView
import com.zykrave.anirumy.feature.activitydetails.publish.PublishActivityView
import com.zykrave.anirumy.feature.calendar.CalendarView
import com.zykrave.anirumy.feature.characterdetails.CharacterDetailsView
import com.zykrave.anirumy.feature.explore.charts.MediaChartListView
import com.zykrave.anirumy.feature.explore.discover.DiscoverView
import com.zykrave.anirumy.feature.explore.search.SearchView
import com.zykrave.anirumy.feature.explore.season.SeasonAnimeView
import com.zykrave.anirumy.feature.home.HomeView
import com.zykrave.anirumy.feature.home.current.fulllist.CurrentFullListView
import com.zykrave.anirumy.feature.login.LoginView
import com.zykrave.anirumy.feature.mediadetails.MediaDetailsView
import com.zykrave.anirumy.feature.mediadetails.activity.MediaActivityView
import com.zykrave.anirumy.feature.mediadetails.characters.MediaCharactersView
import com.zykrave.anirumy.feature.notifications.NotificationsView
import com.zykrave.anirumy.feature.profile.ProfileView
import com.zykrave.anirumy.feature.profile.favorites.reorder.ReorderFavoritesView
import com.zykrave.anirumy.feature.reviewdetails.ReviewDetailsView
import com.zykrave.anirumy.feature.settings.ContributorsView
import com.zykrave.anirumy.feature.settings.SettingsView
import com.zykrave.anirumy.feature.settings.TranslationsView
import com.zykrave.anirumy.feature.settings.customlists.CustomListsView
import com.zykrave.anirumy.feature.settings.liststyle.ListStyleSettingsView
import com.zykrave.anirumy.feature.settings.priority_colors.PriorityColorView
import com.zykrave.anirumy.feature.staffdetails.StaffDetailsView
import com.zykrave.anirumy.feature.studiodetails.StudioDetailsView
import com.zykrave.anirumy.feature.thread.ThreadDetailsView
import com.zykrave.anirumy.feature.thread.comment.ThreadCommentDetailsView
import com.zykrave.anirumy.feature.thread.publish.PublishCommentView
import com.zykrave.anirumy.feature.usermedialist.UserMediaListHostView

private val topNavigationTransitionSpec = NavDisplay.transitionSpec {
    ContentTransform(
        fadeIn(animationSpec = tween()),
        fadeOut(animationSpec = tween()),
    )
} + NavDisplay.popTransitionSpec {
    ContentTransform(
        fadeIn(animationSpec = tween()),
        fadeOut(animationSpec = tween()),
    )
} + NavDisplay.predictivePopTransitionSpec {
    ContentTransform(
        fadeIn(spring(dampingRatio = 1f, stiffness = 1600f)),
        fadeOut(spring(dampingRatio = 1f, stiffness = 1600f))
    )
}

@Composable
fun MainNavigation(
    navigator: Navigator,
    isCompactScreen: Boolean,
    isLoggedIn: Boolean,
    homeTab: HomeTab,
    deepLink: DeepLink?,
    padding: PaddingValues = PaddingValues(),
) {
    val context = LocalContext.current
    val navActionManager = LocalNavActionManager.current
    val bottomPadding by animateDpAsState(
        targetValue = padding.calculateBottomPadding(),
        label = "bottom_bar_padding"
    )

    var spoilerText by remember { mutableStateOf<String?>(null) }
    val markdownUriHandler = remember {
        MarkdownUriHandler(
            onSpoilerClicked = { spoilerText = it },
            onLinkClicked = { context.openActionView(it) },
        )
    }

    spoilerText?.let {
        SpoilerSheet(
            text = it,
            uriHandler = markdownUriHandler,
            onDismiss = { spoilerText = null }
        )
    }


    LaunchedEffect(deepLink) {
        if (deepLink != null) {
            when (deepLink.type) {
                DeepLink.Type.ANIME, DeepLink.Type.MANGA -> {
                    deepLink.id.toIntOrNull()?.let { navActionManager.toMediaDetails(it) }
                }

                DeepLink.Type.USER -> {
                    navActionManager.toUserDetails(
                        userId = deepLink.id.toIntOrNull(),
                        username = deepLink.id
                    )
                }

                DeepLink.Type.SEARCH -> navActionManager.toSearch()

                DeepLink.Type.CHARACTER -> {
                    deepLink.id.toIntOrNull()?.let { navActionManager.toCharacterDetails(it) }
                }

                DeepLink.Type.STAFF -> {
                    deepLink.id.toIntOrNull()?.let { navActionManager.toStaffDetails(it) }
                }

                DeepLink.Type.STUDIO -> {
                    deepLink.id.toIntOrNull()?.let { navActionManager.toStudioDetails(it) }
                }

                DeepLink.Type.THREAD -> {
                    deepLink.id.toIntOrNull()?.let { navActionManager.toThreadDetails(it) }
                }

                DeepLink.Type.ACTIVITY -> {
                    deepLink.id.toIntOrNull()?.let { navActionManager.toActivityDetails(it) }
                }
            }
        }
    }

    val entryProvider = entryProvider<NavKey> {
        entry<Route.Home>(
            metadata = topNavigationTransitionSpec
        ) {
            HomeView(
                isLoggedIn = isLoggedIn,
                defaultHomeTab = homeTab,
                modifier = if (isCompactScreen) Modifier.padding(bottom = bottomPadding) else Modifier,
            )
        }

        entry<Route.AnimeTab>(
            metadata = topNavigationTransitionSpec
        ) {
            if (isLoggedIn) {
                UserMediaListHostView(
                    arguments = Route.UserMediaList(
                        mediaType = MediaType.ANIME.rawValue,
                    ),
                    isCompactScreen = isCompactScreen,
                    modifier = Modifier.padding(bottom = bottomPadding),
                )
            } else {
                LoginView()
            }
        }

        entry<Route.MangaTab>(
            metadata = topNavigationTransitionSpec
        ) {
            if (isLoggedIn) {
                UserMediaListHostView(
                    arguments = Route.UserMediaList(
                        mediaType = MediaType.MANGA.rawValue,
                    ),
                    isCompactScreen = isCompactScreen,
                    modifier = Modifier.padding(bottom = bottomPadding),
                )
            } else {
                LoginView()
            }
        }

        entry<Route.Profile>(
            metadata = topNavigationTransitionSpec
        ) {
            if (isLoggedIn) {
                ProfileView(
                    arguments = Route.UserDetails(null, null),
                    modifier = if (isCompactScreen) Modifier.padding(bottom = bottomPadding) else Modifier,
                )
            } else {
                LoginView(
                    showSettingsButton = true,
                    navigateToSettings = navActionManager::toSettings
                )
            }
        }

        entry<Route.Explore>(
            metadata = topNavigationTransitionSpec
        ) {
            DiscoverView(
                isLoggedIn = isLoggedIn,
                contentPadding = if (isCompactScreen) PaddingValues(bottom = bottomPadding) else PaddingValues(),
            )
        }

        entry<Route.UserDetails> {
            ProfileView(
                arguments = it,
            )
        }

        entry<Route.UserMediaList> {
            UserMediaListHostView(
                arguments = it,
                isCompactScreen = isCompactScreen,
                modifier = Modifier.padding(bottom = bottomPadding),
            )
        }

        entry<Route.Search> {
            SearchView(
                arguments = it,
                isLoggedIn = isLoggedIn,
                modifier = Modifier.padding(bottom = bottomPadding),
            )
        }

        entry<Route.Notifications> {
            if (isLoggedIn) {
                NotificationsView(
                    arguments = it,
                )
            } else {
                LoginView()
            }
        }

        entry<Route.MediaDetails> {
            MediaDetailsView(
                arguments = it.copy(isLoggedIn = isLoggedIn),
            )
        }

        entry<Route.MediaChartList> {
            MediaChartListView(
                arguments = it,
                isLoggedIn = isLoggedIn,
            )
        }

        entry<Route.SeasonAnime> {
            SeasonAnimeView(
                isLoggedIn = isLoggedIn,
                arguments = it,
            )
        }

        entry<Route.Calendar> {
            CalendarView(
                isLoggedIn = isLoggedIn,
            )
        }

        entry<Route.CharacterDetails> {
            CharacterDetailsView(
                isLoggedIn = isLoggedIn,
                arguments = it,
            )
        }

        entry<Route.StaffDetails> {
            StaffDetailsView(
                isLoggedIn = isLoggedIn,
                arguments = it,
            )
        }

        entry<Route.ReviewDetails> {
            ReviewDetailsView(
                arguments = it,
            )
        }

        entry<Route.ThreadDetails> {
            ThreadDetailsView(
                arguments = it,
            )
        }

        entry<Route.ThreadCommentDetails> {
            ThreadCommentDetailsView(
                arguments = it,
            )
        }

        entry<Route.StudioDetails> {
            StudioDetailsView(
                arguments = it,
            )
        }

        entry<Route.Settings> {
            SettingsView()
        }
        entry<Route.ListStyleSettings> {
            ListStyleSettingsView()
        }
        entry<Route.CustomLists> {
            CustomListsView()
        }
        entry<Route.Translations> {
            TranslationsView()
        }
        entry<Route.Contributors> {
            ContributorsView()
        }

        entry<Route.FullScreenImage> {
            FullScreenImageView(
                arguments = it,
                isCompactScreen = isCompactScreen,
                onDismiss = navActionManager::goBack
            )
        }

        entry<Route.ActivityDetails> {
            ActivityDetailsView(
                arguments = it,
            )
        }

        entry<Route.PublishActivity> {
            if (isLoggedIn) {
                PublishActivityView(
                    arguments = it,
                )
            } else {
                LoginView()
            }
        }

        entry<Route.PublishComment> {
            if (isLoggedIn) {
                PublishCommentView(
                    arguments = it,
                )
            } else {
                LoginView()
            }
        }

        entry<Route.MediaActivity> {
            MediaActivityView(
                arguments = it,
            )
        }

        entry<Route.MediaCharacters> {
            MediaCharactersView(
                arguments = it,
            )
        }

        entry<Route.CurrentFullList> {
            CurrentFullListView(
                isLoggedIn = isLoggedIn,
                listType = it.listType,
            )
        }

        entry<Route.ReorderFavorites> {
            ReorderFavoritesView(
                arguments = it,
            )
        }

        entry<Route.PriorityColors> {
            PriorityColorView()
        }
    }

    CompositionLocalProvider(LocalMarkdownUriHandler provides markdownUriHandler) {
        NavDisplay(
            entries = navigator.state.toDecoratedEntries(entryProvider),
            modifier = Modifier.padding(
                start = padding.calculateStartPadding(LocalLayoutDirection.current),
                top = padding.calculateTopPadding(),
                end = padding.calculateEndPadding(LocalLayoutDirection.current),
            ),
            transitionSpec = {
                // Slide in from right when navigating forward
                (slideInHorizontally(initialOffsetX = { it })) togetherWith
                        (slideOutHorizontally(targetOffsetX = { -it })
                                + fadeOut(animationSpec = tween()))
            },
            popTransitionSpec = {
                // Slide in from left when navigating back
                (slideInHorizontally(initialOffsetX = { -it }) + fadeIn()) togetherWith
                        slideOutHorizontally(targetOffsetX = { it })
            },
            predictivePopTransitionSpec = {
                // Slide in from left when navigating back
                (slideInHorizontally(initialOffsetX = { -it })
                        + fadeIn(animationSpec = tween())) togetherWith
                        (slideOutHorizontally(targetOffsetX = { it }))
            },
            onBack = navigator::goBack,
        )
    }
}
