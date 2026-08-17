package com.zykrave.anirumy

import com.zykrave.anirumy.feature.activitydetails.ActivityDetailsViewModel
import com.zykrave.anirumy.feature.activitydetails.publish.PublishActivityViewModel
import com.zykrave.anirumy.feature.calendar.CalendarHostViewModel
import com.zykrave.anirumy.feature.calendar.CalendarViewModel
import com.zykrave.anirumy.feature.characterdetails.CharacterDetailsViewModel
import com.zykrave.anirumy.feature.editmedia.EditMediaViewModel
import com.zykrave.anirumy.feature.explore.charts.MediaChartViewModel
import com.zykrave.anirumy.feature.explore.discover.DiscoverViewModel
import com.zykrave.anirumy.feature.explore.search.SearchViewModel
import com.zykrave.anirumy.feature.explore.search.genretag.GenresTagsViewModel
import com.zykrave.anirumy.feature.explore.season.SeasonAnimeViewModel
import com.zykrave.anirumy.feature.home.HomeViewModel
import com.zykrave.anirumy.feature.home.activity.ActivityFeedViewModel
import com.zykrave.anirumy.feature.home.current.CurrentViewModel
import com.zykrave.anirumy.feature.mediadetails.MediaDetailsViewModel
import com.zykrave.anirumy.feature.mediadetails.activity.MediaActivityViewModel
import com.zykrave.anirumy.feature.mediadetails.characters.MediaCharactersViewModel
import com.zykrave.anirumy.feature.notifications.NotificationsViewModel
import com.zykrave.anirumy.feature.profile.ProfileViewModel
import com.zykrave.anirumy.feature.profile.favorites.UserFavoritesViewModel
import com.zykrave.anirumy.feature.profile.favorites.reorder.ReorderFavoritesViewModel
import com.zykrave.anirumy.feature.profile.social.UserSocialViewModel
import com.zykrave.anirumy.feature.profile.stats.UserStatsViewModel
import com.zykrave.anirumy.feature.reviewdetails.ReviewDetailsViewModel
import com.zykrave.anirumy.feature.settings.SettingsViewModel
import com.zykrave.anirumy.feature.settings.customlists.CustomListsViewModel
import com.zykrave.anirumy.feature.settings.liststyle.ListStyleSettingsViewModel
import com.zykrave.anirumy.feature.settings.priority_colors.PriorityColorViewModel
import com.zykrave.anirumy.feature.staffdetails.StaffDetailsViewModel
import com.zykrave.anirumy.feature.studiodetails.StudioDetailsViewModel
import com.zykrave.anirumy.feature.thread.ThreadDetailsViewModel
import com.zykrave.anirumy.feature.thread.comment.ThreadCommentViewModel
import com.zykrave.anirumy.feature.thread.publish.PublishCommentViewModel
import com.zykrave.anirumy.feature.usermedialist.UserMediaListViewModel
import com.zykrave.anirumy.ui.screens.main.MainViewModel
import org.koin.dsl.module
import org.koin.plugin.module.dsl.viewModel

val viewModelModule = module {
    viewModel<MainViewModel>()
    viewModel<ActivityDetailsViewModel>()
    viewModel<PublishActivityViewModel>()
    viewModel<CalendarViewModel>()
    viewModel<CalendarHostViewModel>()
    viewModel<CharacterDetailsViewModel>()
    viewModel<EditMediaViewModel>()
    viewModel<SearchViewModel>()
    viewModel<GenresTagsViewModel>()
    viewModel<MediaChartViewModel>()
    viewModel<SeasonAnimeViewModel>()
    viewModel<HomeViewModel>()
    viewModel<ActivityFeedViewModel>()
    viewModel<CurrentViewModel>()
    viewModel<DiscoverViewModel>()
    viewModel<MediaDetailsViewModel>()
    viewModel<MediaActivityViewModel>()
    viewModel<NotificationsViewModel>()
    viewModel<ProfileViewModel>()
    viewModel<UserFavoritesViewModel>()
    viewModel<UserSocialViewModel>()
    viewModel<UserStatsViewModel>()
    viewModel<ReviewDetailsViewModel>()
    viewModel<SettingsViewModel>()
    viewModel<CustomListsViewModel>()
    viewModel<ListStyleSettingsViewModel>()
    viewModel<StaffDetailsViewModel>()
    viewModel<StudioDetailsViewModel>()
    viewModel<ThreadDetailsViewModel>()
    viewModel<ThreadCommentViewModel>()
    viewModel<PublishCommentViewModel>()
    viewModel<UserMediaListViewModel>()
    viewModel<ReorderFavoritesViewModel>()
    viewModel<PriorityColorViewModel>()
    viewModel<MediaCharactersViewModel>()
}