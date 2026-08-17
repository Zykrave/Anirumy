package com.zykrave.anirumy.feature.studiodetails

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.base.event.PagedEvent
import com.zykrave.anirumy.core.base.event.UiEvent

@Immutable
interface StudioDetailsEvent : UiEvent, PagedEvent {
    fun toggleFavorite()
}