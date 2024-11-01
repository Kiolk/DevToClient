package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ReactionsForArticleApi(
    @SerialName("current_user")
    val currentUser: CurrentUser?,
    @SerialName("article_reaction_counts")
    val articleReactionCounts: List<ArticleReactionCount>? = emptyList()
)

@Serializable
data class CurrentUser(
    val id: Int? = 0
)

@Serializable
data class ArticleReactionCount(
    val category: String? = null,
    val count: Int? = null
)
