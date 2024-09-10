package com.github.kiolk.devto.di

import com.github.kiolk.devto.Platform
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val appModule = module {

}

val platformModule = module {
    singleOf(::Platform)
}
