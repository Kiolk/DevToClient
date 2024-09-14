package com.github.kiolk.devto

import android.app.Application
import com.github.kiolk.devto.di.PlatformSpecificModule
import com.github.kiolk.devto.di.appModule
import com.github.kiolk.devto.di.networkModule
import com.github.kiolk.devto.di.platformModule
import com.github.kiolk.devto.di.repositoryModule
import com.github.kiolk.devto.di.screenModelModule
import com.github.kiolk.devto.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DevToApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@DevToApp)
            modules(
                appModule,
                platformModule,
                networkModule,
                repositoryModule,
                useCaseModule,
                screenModelModule,
                PlatformSpecificModule.getModule()
            )
        }
    }
}
