package com.zykrave.anirumy.feature.settings.customlists

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.base.event.UiEvent
import com.zykrave.anirumy.core.network.type.MediaType

@Immutable
interface CustomListsEvent : UiEvent {
    fun onListAdded(list: String, mediaType: MediaType)
    fun onListRemoved(list: String, mediaType: MediaType)
    fun updateCustomLists()
}