package com.github.kiolk.devto.domain.models

sealed class SearchType(val value: String) {
    data object Article : SearchType("Article")
    data object User : SearchType("User")
    data object Organization : SearchType("Organization")
    data object Comment : SearchType("Comment")
    data object Tag : SearchType("Tag")

    companion object {
        fun getType(type: String?): SearchType {
            return when (type) {
                Article.value -> Article
                User.value -> User
                Organization.value -> Organization
                Tag.value -> Tag
                Comment.value -> Comment
                else -> Article
            }
        }
    }
}
