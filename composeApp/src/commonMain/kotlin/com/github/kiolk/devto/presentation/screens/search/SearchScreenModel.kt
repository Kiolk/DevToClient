package com.github.kiolk.devto.presentation.screens.search

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.github.kiolk.devto.domain.models.SearchParameters
import com.github.kiolk.devto.domain.models.SearchType
import com.github.kiolk.devto.domain.models.Searchable
import com.github.kiolk.devto.domain.usecases.SearchUseCase
import com.github.kiolk.devto.presentation.screens.home.SortingTypeUi
import com.github.kiolk.devto.utils.pagination.Pagination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class SearchScreenModel(private val searchUseCase: SearchUseCase) : ScreenModel {

    private val _searchState: MutableStateFlow<List<Searchable>> =
        MutableStateFlow(emptyList())
    val searchState = _searchState.asStateFlow()

    private val _sortingType: MutableStateFlow<SortingTypeUi> =
        MutableStateFlow(SortingTypeUi.Related)
    val sortingType: StateFlow<SortingTypeUi> = _sortingType

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    val pagination: Pagination<Searchable> = Pagination<Searchable>(
        source = { page ->
            _isLoading.value = true
            searchUseCase(SearchParameters(page = page, searchType = SearchType.Article))
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
}
