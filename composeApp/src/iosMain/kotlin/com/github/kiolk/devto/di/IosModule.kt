package com.github.kiolk.devto.di

import com.github.kiolk.devto.utils.IosStringProvider
import com.github.kiolk.devto.utils.localisation.StringProvider
import org.koin.dsl.module

val iosModule = module {
    single<StringProvider> { IosStringProvider() }
}
