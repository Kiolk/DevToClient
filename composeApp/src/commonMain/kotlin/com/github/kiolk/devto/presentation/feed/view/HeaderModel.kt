package com.github.kiolk.devto.presentation.feed.view

import cafe.adriel.voyager.core.model.ScreenModel
import com.github.kiolk.devto.domain.models.Article
import com.github.kiolk.devto.domain.models.Comment
import com.github.kiolk.devto.domain.models.Organization
import com.github.kiolk.devto.domain.models.Searchable
import com.github.kiolk.devto.domain.models.Tag
import com.github.kiolk.devto.domain.models.User
import com.github.kiolk.devto.presentation.screens.search.mapper.mapToTagUi
import com.github.kiolk.devto.presentation.screens.search.model.SearchableUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HeaderModel(searchable: Searchable) : ScreenModel {

    private val _searchableItem: MutableStateFlow<SearchableUi> =
        MutableStateFlow(object : SearchableUi {})
    val searchableItem: StateFlow<SearchableUi> = _searchableItem

    init {
        _searchableItem.value = when (searchable) {
            is Article -> TODO()
            is Comment -> TODO()
            is Organization -> TODO()
            is Tag -> searchable.mapToTagUi()
            is User -> TODO()
        }
    }
}
