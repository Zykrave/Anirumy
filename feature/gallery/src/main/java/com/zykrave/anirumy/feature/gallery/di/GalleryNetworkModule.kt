package com.zykrave.anirumy.feature.gallery.di

import com.zykrave.anirumy.feature.gallery.data.remote.NekosBestApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create

private const val NEKOS_BEST_BASE_URL = "https://nekos.best/api/v2/"

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

    single<GalleryRepository>()
}
