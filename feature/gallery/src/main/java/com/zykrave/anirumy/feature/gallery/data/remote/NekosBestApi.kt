package com.zykrave.anirumy.feature.gallery.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

@Serializable
data class NekosBestDimensions(
    val width: Int,
    val height: Int,
)

@Serializable
data class NekosBestResult(
    val url: String,
    val dimensions: NekosBestDimensions,
    @SerialName("artist_name") val artistName: String? = null,
    @SerialName("artist_href") val artistHref: String? = null,
    @SerialName("source_url") val sourceUrl: String? = null,
    @SerialName("anime_name") val animeName: String? = null,
)

@Serializable
data class NekosBestResponse(
    val results: List<NekosBestResult>,
)

@Serializable
data class NekosBestCategoryInfo(
    val format: String,
)

interface NekosBestApi {
    @GET("endpoints")
    suspend fun getCategories(): Map<String, NekosBestCategoryInfo>

    @GET("{category}")
    suspend fun getImages(
        @Path("category") category: String,
        @Query("amount") amount: Int = 20,
    ): NekosBestResponse
}
