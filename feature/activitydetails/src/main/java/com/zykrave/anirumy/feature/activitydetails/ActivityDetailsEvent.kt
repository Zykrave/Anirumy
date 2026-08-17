package com.zykrave.anirumy.feature.activitydetails

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.base.event.UiEvent

@Immutable
interface ActivityDetailsEvent : UiEvent {
    fun toggleLikeActivity()
    fun toggleLikeReply(id: Int)
    fun refresh()
}