package com.zykrave.anirumy.feature.gallery.data.remote

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import retrofit2.http.GET
import retrofit2.http.Query

@Serializable
data class WaifuImArtist(
    @SerialName("artist_id") val artistId: Int? = null,
    val name: String? = null,
    val twitter: String? = null,
    val pixiv: String? = null,
    val patreon: String? = null,
    @SerialName("deviant_art") val deviantArt: String? = null,
)

@Serializable
data class WaifuImTag(
    @SerialName("tag_id") val tagId: Int,
    val name: String,
    val description: String? = null,
    @SerialName("is_nsfw") val isNsfw: Boolean = false,
)

@Serializable
data class WaifuImImage(
    @SerialName("image_id") val imageId: Int,
    val url: String,
    val extension: String? = null,
    val width: Int,
    val height: Int,
    @SerialName("byte_size") val byteSize: Long? = null,
    @SerialName("is_nsfw") val isNsfw: Boolean = false,
    val source: String? = null,
    val artist: WaifuImArtist? = null,
    val tags: List<WaifuImTag> = emptyList(),
)

@Serializable
data class WaifuImSearchResponse(
    val images: List<WaifuImImage>,
)

interface WaifuImApi {
    @GET("search")
    suspend fun search(
        @Query("included_tags") includedTags: List<String>,
        @Query("is_nsfw") isNsfw: Boolean = false,
        @Query("many") many: Boolean = true,
    ): WaifuImSearchResponse
}
