package com.zykrave.anirumy.feature.staffdetails

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.base.event.UiEvent
import com.zykrave.anirumy.core.model.staff.StaffMediaGrouped
import com.zykrave.anirumy.core.network.fragment.BasicMediaListEntry

@Immutable
interface StaffDetailsEvent : UiEvent {
    fun setMediaOnMyList(value: Boolean?)
    fun toggleFavorite()
    fun loadNextPageMedia()
    fun selectMediaItem(value: Pair<Int, StaffMediaGrouped>?)
    fun onUpdateListEntry(newListEntry: BasicMediaListEntry?)
    fun loadNextPageCharacters()
    fun setCharactersOnMyList(value: Boolean?)
}