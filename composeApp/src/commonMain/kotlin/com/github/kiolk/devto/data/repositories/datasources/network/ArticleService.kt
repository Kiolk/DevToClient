package com.github.kiolk.devto.data.repositories.datasources.network

import com.github.kiolk.devto.data.repositories.datasources.network.models.ArticleApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.CommentApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.FeedApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.GetArticlesParamsApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.ReactionApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.SearchableApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.SingleArticleApi
import com.github.kiolk.devto.domain.models.SearchParameters

interface ArticleService {

    suspend fun getArticles(params: GetArticlesParamsApi): List<ArticleApi>

    suspend fun getFeed(params: GetArticlesParamsApi): List<FeedApi>

    suspend fun toggleReaction(
        reactionCategory: String,
        articleId: Int,
        reactionOn: String
    ): ReactionApi

    suspend fun getArticleById(id: Int): SingleArticleApi

    suspend fun getCommentsForArticle(articleId: Int): List<CommentApi>

    suspend fun search(searchParameters: SearchParameters): List<SearchableApi>
}
