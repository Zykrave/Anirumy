package com.zykrave.anirumy.ui.screens.main

import androidx.compose.runtime.Immutable

@Immutable
interface MainEvent {
    fun saveLastTab(index: Int)
}