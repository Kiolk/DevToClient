package com.github.kiolk.devto.di

import com.github.kiolk.devto.presentation.screens.home.HomeScreenModel
import org.koin.dsl.module

val screenModelModule = module {
    factory<HomeScreenModel> { HomeScreenModel(get(), get()) }
}
