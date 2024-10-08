package com.github.kiolk.devto.presentation.screens.home.models

data class CommentUi(
    val id: String,
    val text: String,
    val userProfileImage: String,
    val userName: String,
    val commentTime: String,
    val children: List<CommentUi> = emptyList(),
)
