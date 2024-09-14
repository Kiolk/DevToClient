package com.github.kiolk.devto.presentation.screens.models

import com.github.kiolk.devto.presentation.models.Article

data class ArticleUi(
    val article: Article,
    val userName: String,
    val organisationName: String?,
    val publishedAt: String,
    val publishedAgo: String,
    val title: String,
    val description: String,
)
