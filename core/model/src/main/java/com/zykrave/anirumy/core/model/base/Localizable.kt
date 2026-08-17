package com.zykrave.anirumy.core.model.base

import androidx.compose.runtime.Composable

fun interface Localizable {
    @Composable
    fun localized(): String
}