package com.github.kiolk.devto.di

import com.github.kiolk.devto.domain.usecases.GetArticleUseCase
import com.github.kiolk.devto.domain.usecases.GetArticleUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetArticleUseCase> { GetArticleUseCaseImpl(get()) }
}
