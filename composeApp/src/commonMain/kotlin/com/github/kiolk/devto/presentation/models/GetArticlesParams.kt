package com.github.kiolk.devto.presentation.models

import com.github.kiolk.devto.data.repositories.datasources.network.models.SortingType
import com.github.kiolk.devto.domain.models.SearchType

data class GetArticlesParams(
    val page: Int? = null,
    val perPage: Int? = null,
    val tag: String? = null,
    val tags: String? = null,
    val tagsExclude: String? = null,
    val username: String? = null,
    val state: String? = null,
    val top: Int? = null,
    val collectionId: Int? = null,
    val sortingType: SortingType = SortingType.Infinity,
    val type: SearchType = SearchType.Article
)
