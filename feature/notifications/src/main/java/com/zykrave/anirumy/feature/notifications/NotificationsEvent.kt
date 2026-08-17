package com.zykrave.anirumy.feature.notifications

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.model.notification.NotificationTypeGroup
import com.zykrave.anirumy.core.base.event.PagedEvent
import com.zykrave.anirumy.core.base.event.UiEvent

@Immutable
interface NotificationsEvent : UiEvent, PagedEvent {
    fun setType(value: NotificationTypeGroup)
}