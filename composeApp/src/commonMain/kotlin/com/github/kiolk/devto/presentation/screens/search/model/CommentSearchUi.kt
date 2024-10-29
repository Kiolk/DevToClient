package com.github.kiolk.devto.presentation.screens.search.model

import com.github.kiolk.devto.domain.models.Comment

data class CommentSearchUi(
    val comment: Comment,
    val publishedAt: String,
    val articleTitle: String = "Some article title",
) : SearchableUi
