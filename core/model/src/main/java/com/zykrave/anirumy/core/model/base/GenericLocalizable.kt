package com.zykrave.anirumy.core.model.base

import androidx.compose.runtime.Composable

data class GenericLocalizable<T>(
    val value: T
) : Localizable {
    @Composable
    override fun localized() = value.toString()
}