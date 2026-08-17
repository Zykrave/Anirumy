package com.zykrave.anirumy.core.domain.repository

import com.zykrave.anirumy.core.network.api.ReviewApi
import com.zykrave.anirumy.core.network.type.ReviewRating

class ReviewRepository (
    private val api: ReviewApi,
    defaultPreferencesRepository: DefaultPreferencesRepository,
) : BaseNetworkRepository(defaultPreferencesRepository) {

    fun getReviewDetails(reviewId: Int) = api
        .reviewDetailsQuery(reviewId)
        .toFlow()
        .asDataResult {
            it.Review
        }

    fun rateReview(reviewId: Int, rating: ReviewRating) = api
        .rateReview(reviewId, rating)
        .toFlow()
        .asDataResult { it.RateReview }
}