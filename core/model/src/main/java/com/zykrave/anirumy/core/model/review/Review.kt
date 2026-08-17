package com.zykrave.anirumy.core.model.review

import com.zykrave.anirumy.core.network.ReviewDetailsQuery

fun ReviewDetailsQuery.Review.userAcceptance() =
    if (ratingAmount != null && rating != null) (rating!! * 100) / ratingAmount!! else 0

fun ReviewDetailsQuery.Review.userRatingsString() =
    "${userAcceptance()}% (${rating ?: 0}/${ratingAmount ?: 0})"