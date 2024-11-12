package com.github.kiolk.devto.di

import com.github.kiolk.devto.utils.theme.ThemeHelper
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

fun initKoin(themeHelper: ThemeHelper) {
    startKoin {
        modules(
            appModule,
            platformModule,
            networkModule,
            repositoryModule,
            useCaseModule,
            screenModelModule,
            PlatformSpecificModule.getModule(),
            module {
                single<ThemeHelper> { themeHelper }
            }
        )
    }
}

expect object PlatformSpecificModule {

    fun getModule(): Module
}
