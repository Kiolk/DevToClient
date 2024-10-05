package com.github.kiolk.devto.di

import com.github.kiolk.devto.data.repositories.articles.ArticleRepository
import com.github.kiolk.devto.data.repositories.articles.ArticleRepositoryImpl
import com.github.kiolk.devto.data.repositories.settings.SettingsRepository
import com.github.kiolk.devto.data.repositories.settings.SettingsRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<ArticleRepository> { ArticleRepositoryImpl(get()) }
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }
}
