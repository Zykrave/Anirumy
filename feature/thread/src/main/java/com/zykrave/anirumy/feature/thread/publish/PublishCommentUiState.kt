package com.zykrave.anirumy.feature.thread.publish

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.base.state.UiState
import com.zykrave.anirumy.core.network.fragment.CommonThreadComment

@Immutable
data class PublishCommentUiState(
    val savedComment: CommonThreadComment? = null,
    override val error: String? = null,
    override val isLoading: Boolean = false,
) : UiState() {
    override fun setError(value: String?) = copy(error = value)
    override fun setLoading(value: Boolean) = copy(isLoading = value)
}
