package com.github.kiolk.devto.di

import com.github.kiolk.devto.presentation.screens.ArticlesScreenModel
import org.koin.dsl.module

val screenModelModule = module {
    factory<ArticlesScreenModel> { ArticlesScreenModel(get(), get()) }
}
