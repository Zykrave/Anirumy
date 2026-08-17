package com.zykrave.anirumy.core.domain.repository

import com.zykrave.anirumy.core.network.api.LikeApi
import com.zykrave.anirumy.core.network.fragment.ListActivityFragment
import com.zykrave.anirumy.core.network.fragment.MessageActivityFragment
import com.zykrave.anirumy.core.network.fragment.TextActivityFragment
import com.zykrave.anirumy.core.network.type.ActivityType
import com.zykrave.anirumy.core.network.type.LikeableType

class LikeRepository(
    private val api: LikeApi,
    defaultPreferencesRepository: DefaultPreferencesRepository,
) : BaseNetworkRepository(defaultPreferencesRepository) {

    suspend fun toggleActivityLike(id: Int, type: ActivityType) = when (type) {
        ActivityType.TEXT -> toggleTextActivityLike(id) { it?.isLiked == true }

        ActivityType.MESSAGE -> toggleMessageActivityLike(id) { it?.isLiked == true }

        else -> toggleListActivityLike(id) { it?.isLiked == true }
    }

    private suspend fun <T> toggleListActivityLike(
        id: Int,
        transform: (ListActivityFragment?) -> T
    ) = api
        .toggleLikeMutation(id, LikeableType.ACTIVITY)
        .execute()
        .asDataResult { data ->
            val details = data.ToggleLikeV2?.onListActivity?.listActivityFragment
            transform(details)
        }

    suspend fun toggleListActivityLike(id: Int) = toggleListActivityLike(id) { it }

    private suspend fun <T> toggleTextActivityLike(
        id: Int,
        transform: (TextActivityFragment?) -> T
    ) = api
        .toggleLikeMutation(id, LikeableType.ACTIVITY)
        .execute()
        .asDataResult { data ->
            val details = data.ToggleLikeV2?.onTextActivity?.textActivityFragment
            transform(details)
        }

    suspend fun toggleTextActivityLike(id: Int) = toggleTextActivityLike(id) { it }

    private suspend fun <T> toggleMessageActivityLike(
        id: Int,
        transform: (MessageActivityFragment?) -> T
    ) = api
        .toggleLikeMutation(id, LikeableType.ACTIVITY)
        .execute()
        .asDataResult { data ->
            val details = data.ToggleLikeV2?.onMessageActivity?.messageActivityFragment
            transform(details)
        }

    suspend fun toggleMessageActivityLike(id: Int) = toggleMessageActivityLike(id) { it }

    suspend fun toggleActivityReplyLike(id: Int) = api
        .toggleLikeMutation(id, LikeableType.ACTIVITY_REPLY)
        .execute()
        .asDataResult {
            it.ToggleLikeV2?.onActivityReply?.activityReplyFragment
        }

    suspend fun toggleThreadLike(id: Int) = api
        .toggleLikeMutation(id, LikeableType.THREAD)
        .execute()
        .asDataResult {
            it.ToggleLikeV2?.onThread?.basicThreadDetails
        }

    suspend fun toggleThreadCommentLike(id: Int) = api
        .toggleLikeMutation(id, LikeableType.THREAD_COMMENT)
        .execute()
        .asDataResult {
            it.ToggleLikeV2?.onThreadComment?.isLiked
        }
}