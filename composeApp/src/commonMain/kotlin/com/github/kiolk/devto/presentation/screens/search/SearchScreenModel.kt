package com.github.kiolk.devto.presentation.screens.search

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.github.kiolk.devto.domain.models.SearchParameters
import com.github.kiolk.devto.domain.models.Searchable
import com.github.kiolk.devto.domain.usecases.SearchUseCase
import com.github.kiolk.devto.presentation.screens.search.model.SearchSortTypeUi
import com.github.kiolk.devto.presentation.screens.search.model.SearchTypeUi
import com.github.kiolk.devto.presentation.screens.search.model.toSortType
import com.github.kiolk.devto.utils.pagination.Pagination
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

@OptIn(FlowPreview::class)
class SearchScreenModel(private val searchUseCase: SearchUseCase) : ScreenModel {

    private val _searchState: MutableStateFlow<List<Searchable>> =
        MutableStateFlow(emptyList())
    val searchState = _searchState.asStateFlow()

    private val _searchText: MutableStateFlow<String> =
        MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _sortingType: MutableStateFlow<SearchSortTypeUi> =
        MutableStateFlow(SearchSortTypeUi.MostRelevant)
    val sortingType: StateFlow<SearchSortTypeUi> = _sortingType

    private val _searchType: MutableStateFlow<SearchTypeUi> =
        MutableStateFlow(SearchTypeUi.Post)
    val searchType: StateFlow<SearchTypeUi> = _searchType

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        screenModelScope.launch {
            _searchText
                .debounce(DEBOUNCE_TIME)
                .collect { text ->
                    if (text.isNotEmpty()) {
                        _searchState.value = emptyList()
                        pagination.restart()
                    }
                }
        }
    }

    val pagination: Pagination<Searchable> = Pagination(
        source = { page ->
            _isLoading.value = true
            searchUseCase(
                SearchParameters(
                    page = page,
                    searchField = _searchText.value,
                    searchType = _searchType.value.mapToSearchType(),
                    sort = _sortingType.value.toSortType(),
                )
            )
        },
        onNewPortionLoaded = ::onNewPortionLoaded,
        scope = screenModelScope,
        startPosition = 0,
    )

    fun onNewPortionLoaded(data: List<Searchable>) {
        _isLoading.value = false
        _searchState.value = data
    }

    fun loadMore() {
        pagination.loadNewPortion()
    }

    fun onSearchByTypeClicked(it: SearchTypeUi) {
        onSortingValueChanged(_searchType, it)
    }

    fun onSortClick(it: SearchSortTypeUi) {
        onSortingValueChanged(_sortingType, it)
    }

    private fun <T> onSortingValueChanged(state: MutableStateFlow<T>, pressedValue: T) {
        if (state.value != pressedValue) {
            state.value = pressedValue
            _searchState.value = emptyList()
            pagination.restart()
        }
    }

    fun onSearchTextChanged(searchText: String) {
        _searchText.value = searchText
    }

    private companion object {
        const val DEBOUNCE_TIME = 500L
    }
}
