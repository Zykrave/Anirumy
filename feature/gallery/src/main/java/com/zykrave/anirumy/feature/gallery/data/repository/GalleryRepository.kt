package com.zykrave.anirumy.feature.gallery.data.repository

import com.zykrave.anirumy.feature.gallery.data.remote.NekosBestApi
import com.zykrave.anirumy.feature.gallery.data.remote.WaifuImApi

enum class GallerySource {
    NEKOS_BEST,
    WAIFU_IM,
    COMING_SOON,
}

fun GallerySource.categories(): List<String> = when (this) {
    GallerySource.NEKOS_BEST -> listOf("waifu", "neko", "kitsune", "husbando")
    GallerySource.WAIFU_IM -> listOf("waifu", "maid")
    GallerySource.COMING_SOON -> emptyList()
}

data class GalleryImage(
    val url: String,
    val width: Int,
    val height: Int,
    val attribution: String? = null,
)

class GalleryRepository(
    private val nekosBestApi: NekosBestApi,
    private val waifuImApi: WaifuImApi,
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
                val response = waifuImApi.getImages(includedTags = listOf(category))
                response.items.map { image ->
                    GalleryImage(
                        url = image.url,
                        width = image.width,
                        height = image.height,
                        attribution = image.artists.firstOrNull()?.name ?: image.source,
                    )
                }
            }
            GallerySource.COMING_SOON -> {
                // Intentionally empty — this source is a placeholder.
                // See GalleryScreen.kt for the UI message shown for this state.
                emptyList()
            }
        }
    }
}
