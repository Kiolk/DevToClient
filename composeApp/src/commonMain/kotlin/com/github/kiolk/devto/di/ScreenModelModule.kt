package com.github.kiolk.devto.di

import com.github.kiolk.devto.domain.models.Searchable
import com.github.kiolk.devto.presentation.feed.FeedScreenModel
import com.github.kiolk.devto.presentation.feed.view.FeedBodyScreenModel
import com.github.kiolk.devto.presentation.feed.view.HeaderModel
import com.github.kiolk.devto.presentation.screens.article.ArticleScreenModel
import com.github.kiolk.devto.presentation.screens.home.HomeScreenModel
import com.github.kiolk.devto.presentation.screens.search.SearchScreenModel
import com.github.kiolk.devto.presentation.screens.user.UserScreenModel
import org.koin.dsl.module

val screenModelModule = module {
    factory<HomeScreenModel> { HomeScreenModel(get(), get(), get()) }
    factory<FeedBodyScreenModel> { (tag: Searchable) -> FeedBodyScreenModel(tag, get(), get(), get()) }
    factory<FeedScreenModel> { (tag: Searchable) -> FeedScreenModel(tag, get(), get(), get()) }
    factory<HeaderModel> { (tag: Searchable) -> HeaderModel(tag) }
    factory<UserScreenModel> { (userName: String) -> UserScreenModel(userName) }
    factory<ArticleScreenModel> { (articleId: String) ->
        ArticleScreenModel(
            articleId,
            getArticleByIdUseCase = get(),
            stringProvider = get()
        )
    }
    factory<SearchScreenModel> { SearchScreenModel(get(), get()) }
}
