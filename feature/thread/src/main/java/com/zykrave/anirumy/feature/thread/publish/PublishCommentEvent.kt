package com.zykrave.anirumy.feature.thread.publish

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.base.event.UiEvent

@Immutable
interface PublishCommentEvent: UiEvent {
    fun setPublished()

    fun publishThreadComment(
        threadId: Int?,
        parentCommentId: Int?,
        id: Int? = null,
        text: String
    )
}