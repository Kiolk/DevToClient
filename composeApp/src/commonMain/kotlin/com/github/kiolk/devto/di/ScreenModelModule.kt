package com.github.kiolk.devto.di

import com.github.kiolk.devto.presentation.screens.article.ArticleScreenModel
import com.github.kiolk.devto.presentation.screens.home.HomeScreenModel
import com.github.kiolk.devto.presentation.screens.search.SearchScreenModel
import com.github.kiolk.devto.presentation.screens.tag.TagScreenModel
import com.github.kiolk.devto.presentation.screens.user.UserScreenModel
import org.koin.dsl.module

val screenModelModule = module {
    factory<HomeScreenModel> { HomeScreenModel(get(), get(), get()) }
    factory<TagScreenModel> { (tagName: String) -> TagScreenModel(tagName) }
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
