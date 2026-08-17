package com.zykrave.anirumy.feature.profile.social

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.base.event.PagedEvent
import com.zykrave.anirumy.core.base.event.UiEvent

@Immutable
interface UserSocialEvent : UiEvent, PagedEvent {
    fun setType(value: UserSocialType)

    fun onRefresh()
}