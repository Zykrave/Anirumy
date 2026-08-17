package com.zykrave.anirumy.feature.settings.priority_colors

import androidx.compose.runtime.Stable
import androidx.compose.ui.graphics.Color
import com.zykrave.anirumy.core.base.event.UiEvent

@Stable
interface PriorityColorEvent : UiEvent {
    fun onHighPriorityColorChanged(color: Color)
    fun onMediumPriorityColorChanged(color: Color)
    fun onLowPriorityColorChanged(color: Color)
}
