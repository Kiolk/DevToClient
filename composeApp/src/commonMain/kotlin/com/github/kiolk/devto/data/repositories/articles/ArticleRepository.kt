package com.github.kiolk.devto.data.repositories.articles

import com.github.kiolk.devto.presentation.models.Article

interface ArticleRepository {

    suspend fun getArticles(): List<Article>
}
