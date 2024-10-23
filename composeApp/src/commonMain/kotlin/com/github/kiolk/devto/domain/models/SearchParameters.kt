package com.github.kiolk.devto.domain.models

import com.github.kiolk.devto.data.repositories.datasources.network.models.SortingType

@Suppress("LongParameterList")
open class SearchParameters(
    val page: Int = 0,
    val searchType: SearchType = SearchType.Article,
    open val searchField: String? = "Android",
    val perPage: Int = 60,
    open val sort: SortDirection? = null,
    open val sortingType: SortingType = SortingType.Infinity,
    open val tag: String? = null,
    open val tagNames: List<String>? = null,
)

data class TagSearchParameters(
    override val tag: String?,
    override val tagNames: List<String>? = null,
    override val sort: SortDirection = SortDirection.DESC,
    override val sortingType: SortingType = SortingType.Infinity,
) : SearchParameters(searchField = null)
