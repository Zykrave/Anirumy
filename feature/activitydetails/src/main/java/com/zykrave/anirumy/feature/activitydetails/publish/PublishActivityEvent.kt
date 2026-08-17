package com.zykrave.anirumy.feature.activitydetails.publish

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.base.event.UiEvent

@Immutable
interface PublishActivityEvent: UiEvent {
    fun publishActivity(
        id: Int? = null,
        text: String
    )

    fun publishActivityReply(
        activityId: Int,
        id: Int? = null,
        text: String
    )
}