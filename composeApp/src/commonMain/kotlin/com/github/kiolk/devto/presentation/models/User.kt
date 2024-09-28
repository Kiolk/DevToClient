package com.github.kiolk.devto.presentation.models

data class User(
    val name: String,
    val username: String,
    val twitterUsername: String? = null,
    val githubUsername: String? = null,
    val websiteUrl: String? = null,
    val profileImage: String = "",
    val profileImage90: String = "",
)
