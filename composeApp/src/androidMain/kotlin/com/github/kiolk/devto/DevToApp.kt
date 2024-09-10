package com.github.kiolk.devto

import android.app.Application
import com.github.kiolk.devto.di.initKoin

class DevToApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }
}
