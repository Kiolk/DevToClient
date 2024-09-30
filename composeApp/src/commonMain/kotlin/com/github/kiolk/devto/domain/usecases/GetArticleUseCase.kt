package com.github.kiolk.devto.domain.usecases

import com.github.kiolk.devto.data.repositories.articles.ArticleRepository
import com.github.kiolk.devto.presentation.models.Article
import com.github.kiolk.devto.presentation.models.GetArticlesParams

interface GetArticleUseCase {

    suspend operator fun invoke(params: GetArticlesParams = GetArticlesParams()): List<Article>
}

class GetArticleUseCaseImpl(private val repository: ArticleRepository) : GetArticleUseCase {

    override suspend operator fun invoke(params: GetArticlesParams): List<Article> {
        return repository.getArticles(params)
    }
}
