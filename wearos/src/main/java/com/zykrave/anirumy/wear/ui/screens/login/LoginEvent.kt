package com.zykrave.anirumy.wear.ui.screens.login

import android.content.Context
import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.base.event.UiEvent

@Immutable
interface LoginEvent : UiEvent {
    fun launchLoginIntent(context: Context)
}