package com.github.kiolk.devto.di

import com.github.kiolk.devto.presentation.screens.home.HomeScreenModel
import com.github.kiolk.devto.presentation.screens.tag.TagScreenModel
import org.koin.dsl.module

val screenModelModule = module {
    factory<HomeScreenModel> { HomeScreenModel(get(), get()) }
    factory<TagScreenModel> { (tagName: String) -> TagScreenModel(tagName) }
}
