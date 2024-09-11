package com.github.kiolk.devto.di

import com.github.kiolk.devto.data.repositories.articles.ArticleRepository
import com.github.kiolk.devto.data.repositories.articles.ArticleRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    single<ArticleRepository> { ArticleRepositoryImpl(get()) }
}
