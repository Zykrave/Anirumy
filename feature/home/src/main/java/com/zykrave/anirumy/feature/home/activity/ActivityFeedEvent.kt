package com.zykrave.anirumy.feature.home.activity

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.model.activity.ActivityTypeGrouped
import com.zykrave.anirumy.core.base.event.PagedEvent
import com.zykrave.anirumy.core.base.event.UiEvent

@Immutable
interface ActivityFeedEvent : UiEvent, PagedEvent {
    fun setIsFollowing(value: Boolean)
    fun setType(value: ActivityTypeGrouped)
    fun setFollowingFilters(value: List<Int>)
    fun getUserFollowing()
    fun refreshList()
    fun toggleLikeActivity(id: Int)
}