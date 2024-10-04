package com.github.kiolk.devto.data.repositories.articles

import com.github.kiolk.devto.domain.models.Reaction
import com.github.kiolk.devto.presentation.models.Article
import com.github.kiolk.devto.presentation.models.GetArticlesParams

interface ArticleRepository {

    suspend fun getArticles(params: GetArticlesParams): List<Article>

    suspend fun toggleReaction(reactionCategory: String, articleId: Int, reactionOn: String): Reaction

}
