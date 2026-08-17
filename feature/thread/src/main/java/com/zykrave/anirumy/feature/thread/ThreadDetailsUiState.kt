package com.zykrave.anirumy.feature.thread

import androidx.compose.runtime.Stable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.zykrave.anirumy.core.model.thread.ChildComment
import com.zykrave.anirumy.core.network.ThreadDetailsQuery
import com.zykrave.anirumy.core.base.state.PagedUiState
import com.zykrave.anirumy.core.model.TranslatorApp

@Stable
data class ThreadDetailsUiState(
    val translatorApp: TranslatorApp = TranslatorApp.DEFAULT,
    val details: ThreadDetailsQuery.Thread? = null,
    val comments: SnapshotStateList<ChildComment> = mutableStateListOf(),
    val isLiked: Boolean = false,
    val isSubscribed: Boolean = false,
    val fetchFromNetwork: Boolean = false,
    override val page: Int = 0,
    override val hasNextPage: Boolean = true,
    override val error: String? = null,
    override val isLoading: Boolean = true,
) : PagedUiState() {
    override fun setError(value: String?) = copy(error = value)
    override fun setLoading(value: Boolean) = copy(isLoading = value)
    override fun setPage(value: Int) = copy(page = value)
    override fun setHasNextPage(value: Boolean) = copy(hasNextPage = value)
}
