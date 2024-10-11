package com.github.kiolk.devto.di

import com.github.kiolk.devto.domain.usecases.GetArticleByIdUseCase
import com.github.kiolk.devto.domain.usecases.GetArticleByIdUseCaseImpl
import com.github.kiolk.devto.domain.usecases.GetArticleUseCase
import com.github.kiolk.devto.domain.usecases.GetArticleUseCaseImpl
import com.github.kiolk.devto.domain.usecases.SearchUseCase
import com.github.kiolk.devto.domain.usecases.SearchUseCaseImpl
import com.github.kiolk.devto.domain.usecases.ToggleReactionUseCase
import com.github.kiolk.devto.domain.usecases.ToggleReactionUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetArticleUseCase> { GetArticleUseCaseImpl(get()) }
    factory<ToggleReactionUseCase> { ToggleReactionUseCaseImpl(get()) }
    factory<GetArticleByIdUseCase> { GetArticleByIdUseCaseImpl(get()) }
    factory<SearchUseCase> { SearchUseCaseImpl(get()) }
}
