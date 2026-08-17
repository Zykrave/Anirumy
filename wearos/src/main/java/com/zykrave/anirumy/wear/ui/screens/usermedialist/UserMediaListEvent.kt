package com.zykrave.anirumy.wear.ui.screens.usermedialist

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.base.event.PagedEvent
import com.zykrave.anirumy.core.base.event.UiEvent

@Immutable
interface UserMediaListEvent: PagedEvent, UiEvent {
    fun refreshList()
}