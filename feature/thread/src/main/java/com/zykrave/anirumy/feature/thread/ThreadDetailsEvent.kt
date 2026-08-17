package com.zykrave.anirumy.feature.thread

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.base.event.PagedEvent
import com.zykrave.anirumy.core.base.event.UiEvent

@Immutable
interface ThreadDetailsEvent : UiEvent, PagedEvent {
    fun toggleLikeThread()
    fun subscribeToThread(subscribe: Boolean)
    suspend fun toggleLikeComment(id: Int): Boolean
    fun refresh()
}