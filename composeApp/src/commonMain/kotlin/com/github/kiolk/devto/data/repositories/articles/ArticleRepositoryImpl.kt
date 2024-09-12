package com.github.kiolk.devto.data.repositories.articles

import com.github.kiolk.devto.data.repositories.datasources.network.ArticleService
import com.github.kiolk.devto.data.repositories.datasources.network.mappers.toArticle
import com.github.kiolk.devto.data.repositories.datasources.network.mappers.toGetArticlesParamsApi
import com.github.kiolk.devto.presentation.models.Article
import com.github.kiolk.devto.presentation.models.GetArticlesParams

class ArticleRepositoryImpl(private val articleService: ArticleService) : ArticleRepository {
    override suspend fun getArticles(params: GetArticlesParams): List<Article> {
        return articleService.getArticles(params.toGetArticlesParamsApi()).map { it.toArticle() }
    }
}

