package com.zykrave.anirumy.feature.editmedia.composables

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zykrave.anirumy.core.model.maxValue
import com.zykrave.anirumy.core.network.type.ScoreFormat
import com.zykrave.anirumy.core.ui.common.LocalScoreFormat
import com.zykrave.anirumy.core.ui.composables.scores.FiveStarRatingView
import com.zykrave.anirumy.core.ui.composables.scores.RatingView
import com.zykrave.anirumy.core.ui.composables.scores.SmileyRatingView

@Composable
fun ScoreView(
    rating: Double?,
    onRatingChanged: (Double?) -> Unit,
    modifier: Modifier = Modifier,
    format: ScoreFormat = LocalScoreFormat.current,
    increments: Double = 1.0
) {
    when (format) {
        ScoreFormat.POINT_10,
        ScoreFormat.POINT_10_DECIMAL,
        ScoreFormat.POINT_100 -> {
            RatingView(
                maxValue = format.maxValue(),
                modifier = modifier,
                showIcon = true,
                rating = rating,
                showAsDecimal = format == ScoreFormat.POINT_10_DECIMAL,
                onRatingChanged = onRatingChanged,
                increments = increments
            )
        }

        ScoreFormat.POINT_5 -> {
            FiveStarRatingView(
                modifier = modifier,
                rating = rating ?: 0.0,
                onRatingChanged = onRatingChanged
            )
        }

        ScoreFormat.POINT_3 -> {
            SmileyRatingView(
                modifier = modifier,
                rating = rating ?: 0.0,
                onRatingChanged = onRatingChanged
            )
        }

        else -> {}
    }
}