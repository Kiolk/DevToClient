package com.github.kiolk.devto.di

import com.github.kiolk.devto.utils.IosStringProvider
import com.github.kiolk.devto.utils.localisation.StringProvider
import com.github.kiolk.devto.utils.theme.ThemeHelper
import com.github.kiolk.devto.utils.theme.ThemeHelperIos
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.ObservableSettings
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.Logger
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults

val iosModule = module {
    single<StringProvider> { IosStringProvider() }
    single<ObservableSettings> { NSUserDefaultsSettings(NSUserDefaults("devto_settings")) }
    single<Logger> { Logger.DEFAULT }
    single<ThemeHelper> { ThemeHelperIos() }
}
