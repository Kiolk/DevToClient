package com.github.kiolk.devto.data.repositories.articles

import com.github.kiolk.devto.presentation.models.Article
import com.github.kiolk.devto.presentation.models.GetArticlesParams

interface ArticleRepository {

    suspend fun getArticles(params: GetArticlesParams): List<Article>
}
