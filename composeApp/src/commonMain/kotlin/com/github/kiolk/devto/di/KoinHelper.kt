package com.github.kiolk.devto.di

import org.koin.core.context.startKoin

fun initKoin() {
    startKoin {
        modules(appModule, platformModule)
    }
}
