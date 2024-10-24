package com.github.kiolk.devto.presentation.screens.search.model

import com.github.kiolk.devto.domain.models.SearchType
import com.github.kiolk.devto.domain.models.SortDirection
import com.github.kiolk.devto.utils.localisation.StringsKeys

sealed class SearchSortTypeUi(override val key: String) : SortTypeUi(key) {
    data object MostRelevant : SearchSortTypeUi(StringsKeys.MOST_RELEVANT)
    data object Newest : SearchSortTypeUi(StringsKeys.NEWEST)
    data object Oldest : SearchSortTypeUi(StringsKeys.OLDEST)

    companion object {
        fun values(): List<SearchSortTypeUi> {
            return listOf(MostRelevant, Oldest, Newest)
        }
    }
}

sealed class SearchTypeUi(override val key: String) : SortTypeUi(key) {
    data object Post : SearchTypeUi(StringsKeys.ARTICLE)
    data object User : SearchTypeUi(StringsKeys.USER)
    data object Organization : SearchTypeUi(StringsKeys.ORGANIZATION)
    data object Comment : SearchTypeUi(StringsKeys.SEARCH_COMMENTS)
    data object Tag : SearchTypeUi(StringsKeys.TAGS)

    fun mapToSearchType(): SearchType {
        return when (this) {
            Comment -> SearchType.Comment
            Organization -> SearchType.Organization
            Post -> SearchType.Article
            Tag -> SearchType.Tag
            User -> SearchType.User
        }
    }

    companion object {
        fun values(): List<SearchTypeUi> {
            return listOf(Post, User, Organization, Comment, Tag)
        }
    }
}

sealed class SortTypeUi(open val key: String)

fun SearchSortTypeUi.toSortType(): SortDirection? {
    return when (this) {
        SearchSortTypeUi.MostRelevant -> null
        SearchSortTypeUi.Newest -> SortDirection.DESC
        SearchSortTypeUi.Oldest -> SortDirection.ASC
    }
}
