package com.zykrave.anirumy.feature.gallery

import androidx.compose.runtime.Stable
import com.zykrave.anirumy.core.base.state.UiState
import com.zykrave.anirumy.feature.gallery.data.repository.GalleryImage
import com.zykrave.anirumy.feature.gallery.data.repository.GallerySource

@Stable
data class GalleryUiState(
    val images: List<GalleryImage> = emptyList(),
    val source: GallerySource = GallerySource.NEKOS_BEST,
    val category: String = "waifu",
    override val isLoading: Boolean = false,
    override val error: String? = null,
) : UiState() {
    override fun setLoading(value: Boolean) = copy(isLoading = value)
    override fun setError(value: String?) = copy(error = value)
}
