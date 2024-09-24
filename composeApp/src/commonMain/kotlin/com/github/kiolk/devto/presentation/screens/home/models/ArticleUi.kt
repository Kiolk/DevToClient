package com.github.kiolk.devto.presentation.screens.home.models

import com.github.kiolk.devto.presentation.models.Article

data class ArticleUi(
    val article: Article,
    val userName: String,
    val organisationName: String?,
    val publishedAt: String,
    val publishedAgo: String,
    val title: String,
    val description: String,
    val tags: List<TagUi> = emptyList(),
    val isBookmarked: Boolean = false,
    val numberOfComments: Int = 0,
    val reactionsUi: ReactionsUi = ReactionsUi(
        listOf(ReactionType.Fire, ReactionType.Head, ReactionType.Hands, ReactionType.Unicorn, ReactionType.Heart),
        5
    )
)
