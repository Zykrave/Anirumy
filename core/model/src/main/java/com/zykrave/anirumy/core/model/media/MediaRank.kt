package com.zykrave.anirumy.core.model.media

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.zykrave.anirumy.core.network.type.MediaRankType
import com.zykrave.anirumy.core.resources.R
import com.zykrave.anirumy.core.resources.stat_dark_red
import com.zykrave.anirumy.core.resources.stat_dark_yellow
import com.zykrave.anirumy.core.resources.stat_light_red
import com.zykrave.anirumy.core.resources.stat_light_yellow

fun MediaRankType.icon() = when (this) {
    MediaRankType.RATED -> R.drawable.star_filled_20
    MediaRankType.POPULAR -> R.drawable.favorite_filled_20
    MediaRankType.UNKNOWN__ -> R.drawable.error_20
}

@Composable
fun MediaRankType.color(): Color {
    val isDark = isSystemInDarkTheme()
    return when (this) {
        MediaRankType.RATED -> if (isDark) stat_dark_yellow else stat_light_yellow
        MediaRankType.POPULAR -> if (isDark) stat_dark_red else stat_light_red
        MediaRankType.UNKNOWN__ -> MaterialTheme.colorScheme.onSurface
    }
}