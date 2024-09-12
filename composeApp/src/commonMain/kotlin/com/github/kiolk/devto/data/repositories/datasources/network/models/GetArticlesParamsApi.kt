package com.github.kiolk.devto.data.repositories.datasources.network.models

data class GetArticlesParamsApi(
    val page: Int? = null,
    val perPage: Int? = null,
    val tag: String? = null,
    val tags: String? = null,
    val tagsExclude: String? = null,
    val username: String? = null,
    val state: String? = null,
    val top: Int? = null,
    val collectionId: Int? = null,
)
