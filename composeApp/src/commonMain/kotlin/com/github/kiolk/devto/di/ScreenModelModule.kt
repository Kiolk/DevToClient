package com.github.kiolk.devto.di

import com.github.kiolk.devto.domain.models.Tag
import com.github.kiolk.devto.presentation.screens.article.ArticleScreenModel
import com.github.kiolk.devto.presentation.screens.home.HomeScreenModel
import com.github.kiolk.devto.presentation.screens.search.SearchScreenModel
import com.github.kiolk.devto.presentation.screens.tag.TagScreenModel
import com.github.kiolk.devto.presentation.screens.user.UserScreenModel
import org.koin.dsl.module

val screenModelModule = module {
    factory<HomeScreenModel> { HomeScreenModel(get(), get(), get()) }
    factory<TagScreenModel> { (tag: Tag) -> TagScreenModel(tag, get(), get(), get()) }
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
