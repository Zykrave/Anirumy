package com.zykrave.anirumy.feature.profile.favorites

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.base.event.PagedEvent
import com.zykrave.anirumy.core.base.event.UiEvent
import com.zykrave.anirumy.core.model.FavoritesType

@Immutable
interface UserFavoritesEvent : UiEvent, PagedEvent {
    fun setType(value: FavoritesType)

    fun updateAfterReorderSaved(result: List<*>)

    fun onRefresh()
}