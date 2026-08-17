package com.zykrave.anirumy.feature.calendar

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.network.AiringAnimesQuery
import com.zykrave.anirumy.core.network.fragment.BasicMediaListEntry
import com.zykrave.anirumy.core.base.event.PagedEvent
import com.zykrave.anirumy.core.base.event.UiEvent

@Immutable
interface CalendarEvent : UiEvent, PagedEvent {
    fun onUpdateListEntry(newListEntry: BasicMediaListEntry?)

    fun selectItem(value: AiringAnimesQuery.AiringSchedule?)
}