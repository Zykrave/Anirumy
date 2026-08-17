package com.zykrave.anirumy.feature.reviewdetails

import androidx.compose.runtime.Immutable
import com.zykrave.anirumy.core.base.event.UiEvent
import com.zykrave.anirumy.core.network.type.ReviewRating

@Immutable
interface ReviewDetailsEvent : UiEvent {
    fun rateReview(rating: ReviewRating)
}