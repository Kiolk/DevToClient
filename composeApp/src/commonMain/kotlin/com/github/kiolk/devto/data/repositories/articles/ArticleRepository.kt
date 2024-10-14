package com.github.kiolk.devto.data.repositories.articles

import com.github.kiolk.devto.domain.models.Article
import com.github.kiolk.devto.domain.models.Comment
import com.github.kiolk.devto.domain.models.Reaction
import com.github.kiolk.devto.domain.models.SearchParameters
import com.github.kiolk.devto.domain.models.Searchable
import com.github.kiolk.devto.presentation.models.GetArticlesParams

interface ArticleRepository {

    suspend fun getArticles(params: GetArticlesParams): List<Article>

    suspend fun toggleReaction(reactionCategory: String, articleId: Int, reactionOn: String): Reaction

    suspend fun getArticleById(id: Int): Article

    suspend fun getCommentsForArticle(id: Int): List<Comment>

    suspend fun search(searchParameters: SearchParameters): List<Searchable>

}
