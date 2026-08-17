package com.zykrave.anirumy

import android.content.Context
import android.net.Uri
import com.zykrave.anirumy.core.common.utils.ContextUtils.showToast

suspend fun Context.startRemoteActivity(data: Uri) {
    showToast("This feature is only available in the Play Store app version")
}