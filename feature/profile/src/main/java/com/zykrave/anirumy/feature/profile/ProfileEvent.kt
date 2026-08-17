package com.zykrave.anirumy.feature.profile

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.base.event.PagedEvent
import com.zykrave.anirumy.core.base.event.UiEvent

@Immutable
interface ProfileEvent : UiEvent, PagedEvent {
    fun toggleFollow()
    fun toggleLikeActivity(id: Int)
    fun deleteActivity(id: Int)
    fun onRefresh()
    fun onRefreshActivities()
}