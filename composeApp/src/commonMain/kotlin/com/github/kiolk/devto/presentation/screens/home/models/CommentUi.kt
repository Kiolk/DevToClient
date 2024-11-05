package com.github.kiolk.devto.presentation.screens.home.models

data class CommentUi(
    val id: String,
    val text: String,
    val userProfileImage: String,
    val name: String = "",
    val userName: String,
    val commentTime: String,
    val children: List<CommentUi> = emptyList(),
    val userId: Int = 0,
    val published: String = "",
    val edited: String = "",
) {
    fun replies(): Int {
        var replays = 0
        children.forEach {
            replays += 1 + it.replies()
        }
        return replays
    }
}
