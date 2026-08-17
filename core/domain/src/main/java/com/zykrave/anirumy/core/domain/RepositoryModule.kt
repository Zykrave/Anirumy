package com.zykrave.anirumy.core.domain

import com.zykrave.anirumy.core.domain.repository.ActivityRepository
import com.zykrave.anirumy.core.domain.repository.CharacterRepository
import com.zykrave.anirumy.core.domain.repository.DefaultPreferencesRepository
import com.zykrave.anirumy.core.domain.repository.FavoriteRepository
import com.zykrave.anirumy.core.domain.repository.LikeRepository
import com.zykrave.anirumy.core.domain.repository.ListPreferencesRepository
import com.zykrave.anirumy.core.domain.repository.LoginRepository
import com.zykrave.anirumy.core.domain.repository.MediaListRepository
import com.zykrave.anirumy.core.domain.repository.MediaRepository
import com.zykrave.anirumy.core.domain.repository.NotificationRepository
import com.zykrave.anirumy.core.domain.repository.ReviewRepository
import com.zykrave.anirumy.core.domain.repository.SearchRepository
import com.zykrave.anirumy.core.domain.repository.StaffRepository
import com.zykrave.anirumy.core.domain.repository.StudioRepository
import com.zykrave.anirumy.core.domain.repository.ThreadRepository
import com.zykrave.anirumy.core.domain.repository.UserRepository
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single
import org.koin.plugin.module.dsl.single

val repositoryModule = module {
    single<ActivityRepository>()
    single<CharacterRepository>()
    single<DefaultPreferencesRepository>()
    single<FavoriteRepository>()
    single<LikeRepository>()
    single<ListPreferencesRepository>()
    single<LoginRepository>()
    single<MediaListRepository>()
    single<MediaRepository>()
    single<NotificationRepository>()
    single<ReviewRepository>()
    single<SearchRepository>()
    single<StaffRepository>()
    single<StudioRepository>()
    single<ThreadRepository>()
    single<UserRepository>()
}