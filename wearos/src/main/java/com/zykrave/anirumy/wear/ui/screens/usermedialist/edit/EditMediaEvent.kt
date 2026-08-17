package com.zykrave.anirumy.wear.ui.screens.usermedialist.edit

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.base.event.UiEvent

@Immutable
interface EditMediaEvent: UiEvent {
    fun onClickPlusOne()
    fun onClickMinusOne()
}