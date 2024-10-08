package com.github.kiolk.devto.presentation.models

import kotlinx.datetime.Instant

data class Comment(
    val commentId: String,
    val userId: Int,
    val text: String,
    val publishedTimestamp: Instant,
    val path: String,
    val username: String,
    val name: String,
    val profileImage90: String,
    val children: List<Comment> = emptyList(),
)
