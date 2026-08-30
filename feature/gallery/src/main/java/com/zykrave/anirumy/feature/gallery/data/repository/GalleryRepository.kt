package com.zykrave.anirumy.feature.gallery.data.repository

import com.zykrave.anirumy.feature.gallery.data.remote.NekosBestApi

enum class GallerySource {
    NEKOS_BEST,
    WAIFU_IM,
    WAIFU_PICS,
}

data class GalleryImage(
    val url: String,
    val width: Int,
    val height: Int,
    val attribution: String? = null,
)

class GalleryRepository(
    private val nekosBestApi: NekosBestApi,
) {
    suspend fun getImages(source: GallerySource, category: String): List<GalleryImage> {
        return when (source) {
            GallerySource.NEKOS_BEST -> {
                val response = nekosBestApi.getImages(category = category, amount = 20)
                response.results.map { result ->
                    GalleryImage(
                        url = result.url,
                        width = result.dimensions.width,
                        height = result.dimensions.height,
                        attribution = result.artistName ?: result.animeName,
                    )
                }
            }
            GallerySource.WAIFU_IM -> {
                // Not yet implemented — added in a later step.
                emptyList()
            }
            GallerySource.WAIFU_PICS -> {
                // Not yet implemented — added in a later step.
                emptyList()
            }
        }
    }
}
