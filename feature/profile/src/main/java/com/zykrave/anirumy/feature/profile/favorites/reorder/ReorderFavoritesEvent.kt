package com.zykrave.anirumy.feature.profile.favorites.reorder


import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.base.event.PagedEvent
import com.zykrave.anirumy.core.base.event.UiEvent

@Immutable
interface ReorderFavoritesEvent : UiEvent, PagedEvent {
    fun onRefresh()

    fun onMove(from: Int, to: Int)

    fun saveNewOrder()
}