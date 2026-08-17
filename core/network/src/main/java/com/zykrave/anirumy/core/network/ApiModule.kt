package com.zykrave.anirumy.core.network

import com.zykrave.anirumy.core.network.api.ActivityApi
import com.zykrave.anirumy.core.network.api.CharacterApi
import com.zykrave.anirumy.core.network.api.FavoriteApi
import com.zykrave.anirumy.core.network.api.LikeApi
import com.zykrave.anirumy.core.network.api.MalApi
import com.zykrave.anirumy.core.network.api.MediaApi
import com.zykrave.anirumy.core.network.api.MediaListApi
import com.zykrave.anirumy.core.network.api.NotificationsApi
import com.zykrave.anirumy.core.network.api.ReviewApi
import com.zykrave.anirumy.core.network.api.StaffApi
import com.zykrave.anirumy.core.network.api.StudioApi
import com.zykrave.anirumy.core.network.api.ThreadApi
import com.zykrave.anirumy.core.network.api.UserApi
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single

val apiModule = module {
    single<ActivityApi>()
    single<CharacterApi>()
    single<FavoriteApi>()
    single<LikeApi>()
    single<MalApi>()
    single<MediaApi>()
    single<MediaListApi>()
    single<NotificationsApi>()
    single<ReviewApi>()
    single<StaffApi>()
    single<StudioApi>()
    single<ThreadApi>()
    single<UserApi>()
}