package com.zykrave.anirumy.core.model.stats.overview

import androidx.compose.runtime.Composable
import com.zykrave.anirumy.core.common.utils.NumberUtils.format
import com.zykrave.anirumy.core.network.MediaStatsQuery
import com.zykrave.anirumy.core.model.base.Colorable
import com.zykrave.anirumy.core.model.base.Localizable
import com.zykrave.anirumy.core.model.point100PrimaryColor
import com.zykrave.anirumy.core.model.stats.StatLocalizableAndColorable

data class ScoreDistribution(
    val score: Int
) : Localizable, Colorable {

    @Composable
    override fun primaryColor() = score.point100PrimaryColor()

    @Composable
    override fun onPrimaryColor() = primaryColor()

    @Composable
    override fun localized(): String = score.format().orEmpty()

    companion object {
        fun MediaStatsQuery.ScoreDistribution.asStat() =
            StatLocalizableAndColorable(
                type = ScoreDistribution(score = score ?: 0),
                value = amount?.toFloat() ?: 0f
            )
    }
}