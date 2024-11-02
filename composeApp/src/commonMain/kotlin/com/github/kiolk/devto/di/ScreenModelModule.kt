package com.github.kiolk.devto.di

import com.github.kiolk.devto.domain.models.Searchable
import com.github.kiolk.devto.presentation.screens.article.ArticleScreenModel
import com.github.kiolk.devto.presentation.screens.article.OpenArticleParams
import com.github.kiolk.devto.presentation.screens.feed.FeedScreenModel
import com.github.kiolk.devto.presentation.screens.feed.view.FeedBodyScreenModel
import com.github.kiolk.devto.presentation.screens.feed.view.FeedParameter
import com.github.kiolk.devto.presentation.screens.feed.view.HeaderScreenModel
import com.github.kiolk.devto.presentation.screens.home.HomeScreenModel
import com.github.kiolk.devto.presentation.screens.search.SearchScreenModel
import com.github.kiolk.devto.presentation.screens.user.UserScreenModel
import org.koin.dsl.module

val screenModelModule = module {
    factory<HomeScreenModel> { HomeScreenModel(get(), get(), get()) }
    factory<FeedBodyScreenModel> { (tag: FeedParameter) ->
        FeedBodyScreenModel(
            tag,
            get(),
            get(),
            get()
        )
    }
    factory<FeedScreenModel> { (tag: Searchable) -> FeedScreenModel(get(), get(), get()) }
    factory<HeaderScreenModel> { (param: FeedParameter) -> HeaderScreenModel(param, get()) }
    factory<UserScreenModel> { (userName: String) -> UserScreenModel(userName) }
    factory<ArticleScreenModel> { (openArticlesParams: OpenArticleParams) ->
        ArticleScreenModel(
            openArticleParams = openArticlesParams,
            getArticleByIdUseCase = get(),
            getArticleByTitleUseCase = get(),
            stringProvider = get()
        )
    }
    factory<SearchScreenModel> { SearchScreenModel(get(), get()) }
}
