package com.github.kiolk.devto.di

import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin() {
    startKoin {
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

expect object PlatformSpecificModule {

    fun getModule(): Module
}
