package com.github.kiolk.devto.domain.usecases

import com.github.kiolk.devto.data.repositories.articles.ArticleRepository
import com.github.kiolk.devto.domain.models.Article
import com.github.kiolk.devto.domain.models.SearchParameters

interface GetArticleByTitleUseCase {

    suspend operator fun invoke(title: String): Article
}

class GetArticleByTitleUseCaseImpl(private val articlesRepository: ArticleRepository) :
    GetArticleByTitleUseCase {

    override suspend fun invoke(title: String): Article {
        val searchResult: Article = articlesRepository.search(
            SearchParameters(
                page = 0,
                searchField = title,
            )
        ).filterIsInstance<Article>().firstOrNull { it.title == title }
            ?: error("Didn't find article with name $title")

        return articlesRepository.getArticleById(searchResult.id)
    }
}
