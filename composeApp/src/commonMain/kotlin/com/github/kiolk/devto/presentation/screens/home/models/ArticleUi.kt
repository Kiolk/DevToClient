package com.github.kiolk.devto.presentation.screens.home.models

import com.github.kiolk.devto.domain.models.Article

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
    val readingTime: Int = 0,
    val topComments: List<CommentUi> = emptyList(),
    val reactionsUi: ReactionsUi = ReactionsUi(),
    val comments: List<CommentUi> = emptyList(),
)
