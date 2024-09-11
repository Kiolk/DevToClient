package com.github.kiolk.devto.data.repositories.articles

import com.github.kiolk.devto.data.repositories.datasources.network.ArticleService
import com.github.kiolk.devto.data.repositories.datasources.network.mappers.toArticle
import com.github.kiolk.devto.presentation.models.Article

class ArticleRepositoryImpl(private val articleService: ArticleService) : ArticleRepository {
    override suspend fun getArticles(): List<Article> {
        return articleService.getArticles().map { it.toArticle() }
    }
}

