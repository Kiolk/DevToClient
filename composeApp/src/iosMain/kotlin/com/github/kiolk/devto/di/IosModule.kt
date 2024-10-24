package com.github.kiolk.devto.di

import com.github.kiolk.devto.utils.IosStringProvider
import com.github.kiolk.devto.utils.localisation.StringProvider
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.Logger
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

val iosModule = module {
    single<StringProvider> { IosStringProvider() }
    single<Settings> { NSUserDefaultsSettings(NSUserDefaults("devto_settings")) }
    single<Logger> { Logger.DEFAULT }
}
