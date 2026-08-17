package com.zykrave.anirumy.feature.mediadetails.characters

import androidx.compose.runtime.Stable
import com.zykrave.anirumy.core.base.event.PagedEvent
import com.zykrave.anirumy.core.base.event.UiEvent

@Stable
interface MediaCharactersEvent : UiEvent, PagedEvent {
    fun onLanguageSelect(language: String)
}