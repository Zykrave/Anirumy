package com.zykrave.anirumy.wear.ui.screens.main

import android.net.Uri
import androidx.compose.runtime.Immutable

@Immutable
interface MainEvent {
    fun onIntentDataReceived(data: Uri?)
}