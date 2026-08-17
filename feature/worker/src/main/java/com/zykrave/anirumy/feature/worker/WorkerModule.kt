package com.zykrave.anirumy.feature.worker

import androidx.work.WorkManager
import org.koin.dsl.module
import org.koin.plugin.module.dsl.worker

val workerModule = module {
    single { WorkManager.getInstance(get()) }
    worker<NotificationWorker>()
}