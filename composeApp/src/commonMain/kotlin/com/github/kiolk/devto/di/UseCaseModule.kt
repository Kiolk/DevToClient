package com.github.kiolk.devto.di

import com.github.kiolk.devto.domain.usecases.GetArticleUseCase
import com.github.kiolk.devto.domain.usecases.GetArticleUseCaseImpl
import com.github.kiolk.devto.domain.usecases.ToggleReactionUseCase
import com.github.kiolk.devto.domain.usecases.ToggleReactionUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetArticleUseCase> { GetArticleUseCaseImpl(get()) }
    factory<ToggleReactionUseCase> { ToggleReactionUseCaseImpl(get()) }
}
