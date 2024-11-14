package com.github.kiolk.devto.di

import com.github.kiolk.devto.domain.usecases.GetAppThemeUseCase
import com.github.kiolk.devto.domain.usecases.GetAppThemeUseCaseImpl
import com.github.kiolk.devto.domain.usecases.GetArticleByIdUseCase
import com.github.kiolk.devto.domain.usecases.GetArticleByIdUseCaseImpl
import com.github.kiolk.devto.domain.usecases.GetArticleByTitleUseCase
import com.github.kiolk.devto.domain.usecases.GetArticleByTitleUseCaseImpl
import com.github.kiolk.devto.domain.usecases.GetArticleUseCase
import com.github.kiolk.devto.domain.usecases.GetArticleUseCaseImpl
import com.github.kiolk.devto.domain.usecases.GetCommentByIdUseCase
import com.github.kiolk.devto.domain.usecases.GetCommentByIdUseCaseImpl
import com.github.kiolk.devto.domain.usecases.GetUserAppThemeUseCase
import com.github.kiolk.devto.domain.usecases.GetUserAppThemeUseCaseImpl
import com.github.kiolk.devto.domain.usecases.GetUserByIdUseCase
import com.github.kiolk.devto.domain.usecases.GetUserByIdUseCaseImpl
import com.github.kiolk.devto.domain.usecases.SearchUseCase
import com.github.kiolk.devto.domain.usecases.SearchUseCaseImpl
import com.github.kiolk.devto.domain.usecases.SetUserAppThemeUseCase
import com.github.kiolk.devto.domain.usecases.SetUserAppThemeUseCaseImpl
import com.github.kiolk.devto.domain.usecases.SystemThemeChangedUseCase
import com.github.kiolk.devto.domain.usecases.SystemThemeChangedUseCaseImpl
import com.github.kiolk.devto.domain.usecases.ToggleReactionUseCase
import com.github.kiolk.devto.domain.usecases.ToggleReactionUseCaseImpl
import org.koin.dsl.module

val useCaseModule = module {
    factory<GetArticleUseCase> { GetArticleUseCaseImpl(get()) }
    factory<ToggleReactionUseCase> { ToggleReactionUseCaseImpl(get()) }
    factory<GetArticleByIdUseCase> { GetArticleByIdUseCaseImpl(get()) }
    factory<GetArticleByTitleUseCase> { GetArticleByTitleUseCaseImpl(get()) }
    factory<SearchUseCase> { SearchUseCaseImpl(get()) }
    factory<GetUserByIdUseCase> { GetUserByIdUseCaseImpl(get()) }
    factory<GetCommentByIdUseCase> { GetCommentByIdUseCaseImpl(get()) }
    factory<GetAppThemeUseCase> { GetAppThemeUseCaseImpl(get(), get()) }
    factory<SystemThemeChangedUseCase> { SystemThemeChangedUseCaseImpl(get()) }
    factory<SetUserAppThemeUseCase> { SetUserAppThemeUseCaseImpl(get()) }
    factory<GetUserAppThemeUseCase> { GetUserAppThemeUseCaseImpl(get()) }
}
