package com.zykrave.anirumy.feature.gallery.data.remote

import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

@Serializable
data class WaifuImArtist(
    val id: Int,
    val name: String,
    val twitter: String? = null,
    val pixiv: String? = null,
    val patreon: String? = null,
    val deviantArt: String? = null,
)

@Serializable
data class WaifuImTag(
    val id: Int,
    val name: String,
    val slug: String? = null,
    val description: String? = null,
)

@Serializable
data class WaifuImImage(
    val id: Int,
    val url: String,
    val extension: String? = null,
    val width: Int,
    val height: Int,
    val byteSize: Long? = null,
    val isNsfw: Boolean = false,
    val source: String? = null,
    val artists: List<WaifuImArtist> = emptyList(),
    val tags: List<WaifuImTag> = emptyList(),
)

@Serializable
data class WaifuImImagesResponse(
    val items: List<WaifuImImage>,
)

interface WaifuImApi {
    @GET("images")
    suspend fun getImages(
        @Header("Accept-Version") acceptVersion: String = "v7",
        @Query("IncludedTags") includedTags: List<String>,
        @Query("IsNsfw") isNsfw: String = "False",
        @Query("PageSize") pageSize: Int = 20,
    ): WaifuImImagesResponse
}
