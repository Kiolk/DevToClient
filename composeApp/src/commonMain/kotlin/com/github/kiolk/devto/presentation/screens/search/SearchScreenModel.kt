package com.github.kiolk.devto.presentation.screens.search

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.github.kiolk.devto.domain.models.SearchParameters
import com.github.kiolk.devto.domain.models.Searchable
import com.github.kiolk.devto.domain.usecases.SearchUseCase
import com.github.kiolk.devto.presentation.screens.search.model.SearchSortTypeUi
import com.github.kiolk.devto.presentation.screens.search.model.SearchTypeUi
import com.github.kiolk.devto.utils.pagination.Pagination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchScreenModel(private val searchUseCase: SearchUseCase) : ScreenModel {

    private val _searchState: MutableStateFlow<List<Searchable>> =
        MutableStateFlow(emptyList())
    val searchState = _searchState.asStateFlow()

    private val _sortingType: MutableStateFlow<SearchSortTypeUi> =
        MutableStateFlow(SearchSortTypeUi.MostRelevant)
    val sortingType: StateFlow<SearchSortTypeUi> = _sortingType

    private val _searchType: MutableStateFlow<SearchTypeUi> =
        MutableStateFlow(SearchTypeUi.Post)
    val searchType: StateFlow<SearchTypeUi> = _searchType

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    val pagination: Pagination<Searchable> = Pagination(
        source = { page ->
            _isLoading.value = true
            searchUseCase(
                SearchParameters(
                    page = page,
                    searchType = _searchType.value.mapToSearchType()
                )
            )
        },
        onNewPortionLoaded = ::onNewPortionLoaded,
        scope = screenModelScope,
        startPosition = 0,
    )

    init {
        pagination.startLoading()
    }

    fun onNewPortionLoaded(data: List<Searchable>) {
        _isLoading.value = false
        _searchState.value = data
    }

    fun loadMore() {
        pagination.loadNewPortion()
    }

    fun onSearchByTypeClicked(it: SearchTypeUi) {
        if (_searchType.value != it) {
            _searchType.value = it
            _searchState.value = emptyList()
            pagination.restart()
        }
    }

    fun onSortClick(it: SearchSortTypeUi) {
        if (_sortingType.value != it) {
            _sortingType.value = it
            _searchState.value = emptyList()
            pagination.restart()
        }
    }
}
