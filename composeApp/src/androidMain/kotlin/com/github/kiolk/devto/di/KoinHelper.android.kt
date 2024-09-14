package com.github.kiolk.devto.di

import org.koin.core.module.Module

actual object PlatformSpecificModule {
    actual fun getModule(): Module {
        return  androidModule
    }
}
