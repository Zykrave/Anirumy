package com.zykrave.anirumy.core.network

import com.zykrave.anirumy.core.network.api.ActivityApi
import com.zykrave.anirumy.core.network.api.CharacterApi
import com.zykrave.anirumy.core.network.api.FavoriteApi
import com.zykrave.anirumy.core.network.api.GithubReleaseApi
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
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

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

    single<GithubReleaseApi> {
        val client: OkHttpClient = get(named("rest"))
        val json = Json { ignoreUnknownKeys = true }
        Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create()
    }
}