package com.github.kiolk.devto.domain.models

data class SearchParameters(
    val page: Int = 0,
    val searchType: String = "Article",
    val searchField: String = "Android",
    val perPage: Int = 60,
)
