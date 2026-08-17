package com.zykrave.anirumy.core.model

import androidx.compose.runtime.Stable

@Stable
data class DeepLink(
    val type: Type,
    val id: String,
) {
    // We could have ANIME and MANGA combined, but AniList urls have the distinction.
    // In the end both of them should open the MediaDetailsView
    enum class Type {
        ANIME, MANGA, CHARACTER, STAFF, STUDIO, USER, SEARCH, THREAD, ACTIVITY
    }
}
