package com.zykrave.anirumy.core.ui.common

import androidx.compose.runtime.staticCompositionLocalOf
import com.zykrave.anirumy.core.network.type.ScoreFormat
import com.zykrave.anirumy.core.ui.common.navigation.NavActionManager
import com.zykrave.anirumy.core.ui.common.navigation.PreviewNavigator
import com.zykrave.anirumy.core.ui.composables.markdown.MarkdownUriHandler
import com.zykrave.anirumy.core.ui.utils.LocaleUtils.getCurrentLanguageTag

val LocalNavActionManager = staticCompositionLocalOf { NavActionManager(PreviewNavigator()) }

val LocalIsLanguageEn = staticCompositionLocalOf {
    getCurrentLanguageTag()?.startsWith("en") == true
}

val LocalBlurAdult = staticCompositionLocalOf { true }

val LocalScoreFormat = staticCompositionLocalOf { ScoreFormat.POINT_10_DECIMAL }

val LocalHideScores = staticCompositionLocalOf { false }

val LocalMarkdownUriHandler = staticCompositionLocalOf { MarkdownUriHandler() }