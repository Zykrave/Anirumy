package com.zykrave.anirumy.feature.explore.charts

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.network.MediaChartQuery
import com.zykrave.anirumy.core.network.fragment.BasicMediaListEntry
import com.zykrave.anirumy.core.base.event.PagedEvent
import com.zykrave.anirumy.core.base.event.UiEvent

@Immutable
interface MediaChartEvent : UiEvent, PagedEvent {
    fun selectItem(value: MediaChartQuery.Medium?)
    fun onUpdateListEntry(newListEntry: BasicMediaListEntry?)
}