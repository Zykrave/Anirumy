package com.zykrave.anirumy.feature.mediadetails.activity

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.base.event.PagedEvent
import com.zykrave.anirumy.core.base.event.UiEvent

@Immutable
interface MediaActivityEvent : UiEvent, PagedEvent {
    fun setIsMine(value: Boolean)
    fun toggleLikeActivity(id: Int)
    fun deleteActivity(id: Int)
}