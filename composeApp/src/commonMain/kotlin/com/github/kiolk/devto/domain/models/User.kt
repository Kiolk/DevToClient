package com.github.kiolk.devto.domain.models

data class User(
    val name: String = "",
    val username: String = "",
    val twitterUsername: String? = null,
    val githubUsername: String? = null,
    val websiteUrl: String? = null,
    val profileImage: String? = null,
    val profileImage90: String = "",
) : Searchable
