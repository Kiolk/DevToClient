package com.github.kiolk.devto.data.repositories.articles

import com.github.kiolk.devto.data.repositories.datasources.network.ArticleService
import com.github.kiolk.devto.data.repositories.datasources.network.mappers.toArticle
import com.github.kiolk.devto.data.repositories.datasources.network.mappers.toGetArticlesParamsApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.ReactionApi
import com.github.kiolk.devto.domain.models.Reaction
import com.github.kiolk.devto.presentation.models.Article
import com.github.kiolk.devto.presentation.models.GetArticlesParams

class ArticleRepositoryImpl(private val articleService: ArticleService) : ArticleRepository {
    override suspend fun getArticles(params: GetArticlesParams): List<Article> {
        return articleService.getFeed(params.toGetArticlesParamsApi()).map { it.toArticle() }
    }

    override suspend fun toggleReaction(reactionCategory: String, articleId: Int, reactionOn: String): Reaction {
        return articleService.toggleReaction(reactionCategory, articleId, reactionOn).toReaction()
    }
}

private fun ReactionApi.toReaction(): Reaction {
    return Reaction(
        result, category, id, reactableId, reactableType,
    )
}

