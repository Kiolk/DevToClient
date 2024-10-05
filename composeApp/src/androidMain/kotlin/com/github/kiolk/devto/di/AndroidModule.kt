package com.github.kiolk.devto.di

import android.content.Context
import com.github.kiolk.devto.utils.AndroidStringProvider
import com.github.kiolk.devto.utils.localisation.StringProvider
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.dsl.module

val androidModule = module {
    single<StringProvider> {
        AndroidStringProvider(get())
    }
    single<Settings> {
        SharedPreferencesSettings(get<Context>().getSharedPreferences("devto_settings", Context.MODE_PRIVATE))
    }
}
