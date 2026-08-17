package com.zykrave.anirumy.feature.characterdetails

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.zykrave.anirumy.core.network.CharacterDetailsQuery
import com.zykrave.anirumy.core.network.CharacterMediaQuery
import com.zykrave.anirumy.core.network.fragment.CommonVoiceActor
import com.zykrave.anirumy.core.base.state.PagedUiState
import com.zykrave.anirumy.core.model.TranslatorApp

@Stable
data class CharacterDetailsUiState(
    val translatorApp: TranslatorApp = TranslatorApp.DEFAULT,
    val character: CharacterDetailsQuery.Character? = null,
    val media: SnapshotStateList<CharacterMediaQuery.Edge> = mutableStateListOf(),
    val isLoadingMedia: Boolean = true,
    val selectedMediaItem: CharacterMediaQuery.Edge? = null,
    val selectedMediaVoiceActors: List<CommonVoiceActor>? = null,
    override val page: Int = 0,
    override val hasNextPage: Boolean = true,
    override val isLoading: Boolean = true,
    override val error: String? = null,
) : PagedUiState() {

    val alternativeNames =
        character?.name?.alternative?.filterNotNull()?.joinToString()
    val alternativeNamesSpoiler =
        character?.name?.alternativeSpoiler?.filterNotNull()?.joinToString()

    override fun setError(value: String?) = copy(error = value)
    override fun setLoading(value: Boolean) = copy(isLoading = value)
    override fun setPage(value: Int) = copy(page = value)
    override fun setHasNextPage(value: Boolean) = copy(hasNextPage = value)
}
