package com.zykrave.anirumy.feature.thread.comment

import androidx.compose.runtime.Stable
import com.zykrave.anirumy.core.base.event.UiEvent
import com.zykrave.anirumy.core.network.fragment.CommonThreadComment

@Stable
interface ThreadCommentEvent : UiEvent {
    suspend fun toggleLikeComment(id: Int): Boolean
    fun onCommentPublished(comment: CommonThreadComment)
}