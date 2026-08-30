package com.zykrave.anirumy.feature.gallery.di

import com.zykrave.anirumy.feature.gallery.data.remote.NekosBestApi
import com.zykrave.anirumy.feature.gallery.data.remote.WaifuImApi
import com.zykrave.anirumy.feature.gallery.data.repository.GalleryRepository
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import org.koin.plugin.module.dsl.single
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

private const val NEKOS_BEST_BASE_URL = "https://nekos.best/api/v2/"
private const val WAIFU_IM_BASE_URL = "https://api.waifu.im/"

val galleryNetworkModule = module {
    single<NekosBestApi> {
        val client: OkHttpClient = get(named("rest"))
        val json = Json { ignoreUnknownKeys = true }
        Retrofit.Builder()
            .baseUrl(NEKOS_BEST_BASE_URL)
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create()
    }

    single<WaifuImApi> {
        val client: OkHttpClient = get(named("rest"))
        val json = Json { ignoreUnknownKeys = true }
        Retrofit.Builder()
            .baseUrl(WAIFU_IM_BASE_URL)
            .client(client)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()
            .create()
    }

    single<GalleryRepository>()
}
