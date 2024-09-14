package com.github.kiolk.devto.di

import com.github.kiolk.devto.utils.AndroidStringProvider
import com.github.kiolk.devto.utils.StringProvider
import org.koin.dsl.module

val androidModule = module {
    single<StringProvider> {
        AndroidStringProvider(get())
    }
}
