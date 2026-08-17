package com.zykrave.anirumy.wear

import android.app.Application
import com.zykrave.anirumy.core.domain.dataStoreModule
import com.zykrave.anirumy.core.domain.repositoryModule
import com.zykrave.anirumy.core.network.apiModule
import com.zykrave.anirumy.core.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            if (BuildConfig.DEBUG) {
                androidLogger()
            }
            androidContext(this@App)
            modules(
                dataStoreModule,
                networkModule,
                apiModule,
                repositoryModule,
                viewModelModule,
            )
        }
    }
}