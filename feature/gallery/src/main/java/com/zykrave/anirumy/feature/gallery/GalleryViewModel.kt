package com.zykrave.anirumy.feature.gallery

import androidx.lifecycle.viewModelScope
import com.zykrave.anirumy.core.common.viewmodel.UiStateViewModel
import com.zykrave.anirumy.feature.gallery.data.GalleryDownloader
import com.zykrave.anirumy.feature.gallery.data.repository.GalleryImage
import com.zykrave.anirumy.feature.gallery.data.repository.GalleryRepository
import com.zykrave.anirumy.feature.gallery.data.repository.GallerySource
import com.zykrave.anirumy.feature.gallery.data.repository.categories
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class GalleryViewModel(
    private val galleryRepository: GalleryRepository,
    private val galleryDownloader: GalleryDownloader,
) : UiStateViewModel<GalleryUiState>() {

    override val initialState = GalleryUiState()

    init {
        fetchImages(initialState.source, initialState.category)
    }

    fun onSourceSelected(source: GallerySource) {
        val newCategory = source.categories().firstOrNull() ?: ""
        mutableUiState.update { it.copy(source = source, category = newCategory) }
        fetchImages(source, newCategory)
    }

    fun onCategorySelected(category: String) {
        mutableUiState.update { it.copy(category = category) }
        fetchImages(mutableUiState.value.source, category)
    }

    fun downloadImage(image: GalleryImage) {
        viewModelScope.launch {
            val fileName = "anirumy_" + System.currentTimeMillis() + ".jpg"
            mutableUiState.update {
                it.copy(downloadStates = it.downloadStates + (image.url to DownloadStatus.Downloading(0f)))
            }
            val result = galleryDownloader.downloadImage(
                url = image.url,
                fileName = fileName,
                onProgress = { progress ->
                    mutableUiState.update {
                        it.copy(downloadStates = it.downloadStates + (image.url to DownloadStatus.Downloading(progress)))
                    }
                }
            )
            val finalStatus = if (result.isSuccess) {
                DownloadStatus.Done
            } else {
                DownloadStatus.Failed(result.exceptionOrNull()?.localizedMessage ?: "Download failed")
            }
            mutableUiState.update {
                it.copy(downloadStates = it.downloadStates + (image.url to finalStatus))
            }
        }
    }

    private fun fetchImages(source: GallerySource, category: String) {
        viewModelScope.launch {
            mutableUiState.update { it.setLoading(true) as GalleryUiState }
            try {
                val images = galleryRepository.getImages(source, category)
                mutableUiState.update { it.copy(isLoading = false, images = images) }
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                showError(e.localizedMessage ?: "Failed to fetch images")
                mutableUiState.update { it.setLoading(false) as GalleryUiState }
            }
        }
    }
}
