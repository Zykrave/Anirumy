package com.zykrave.anirumy.feature.thread.comment

import androidx.compose.runtime.Stable
import com.zykrave.anirumy.core.base.state.UiState
import com.zykrave.anirumy.core.model.TranslatorApp
import com.zykrave.anirumy.core.model.thread.ChildComment

@Stable
data class ThreadCommentUiState(
    val childComment: ChildComment,
    val translatorApp: TranslatorApp = TranslatorApp.DEFAULT,
    val isLiked: Boolean = false,
    override val error: String? = null,
    override val isLoading: Boolean = true,
) : UiState() {
    override fun setError(value: String?) = copy(error = value)
    override fun setLoading(value: Boolean) = copy(isLoading = value)
}
