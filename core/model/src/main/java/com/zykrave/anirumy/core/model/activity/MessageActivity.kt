package com.zykrave.anirumy.core.model.activity

import com.zykrave.anirumy.core.network.fragment.MessageActivityFragment

fun MessageActivityFragment.updateLikeStatus(isLiked: Boolean) = copy(
    isLiked = isLiked,
    likeCount = if (isLiked) likeCount + 1 else likeCount - 1
)