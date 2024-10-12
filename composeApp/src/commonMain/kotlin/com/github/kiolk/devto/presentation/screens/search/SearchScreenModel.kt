package com.github.kiolk.devto.presentation.screens.search

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.github.kiolk.devto.domain.models.SearchParameters
import com.github.kiolk.devto.domain.models.Searchable
import com.github.kiolk.devto.domain.usecases.SearchUseCase
import com.github.kiolk.devto.utils.pagination.Pagination

class SearchScreenModel(private val searchUseCase: SearchUseCase) : ScreenModel {

    val pagination: Pagination<Searchable> = Pagination<Searchable>(
        source = { page ->
            searchUseCase(SearchParameters(page = page))
        },
        onNewPortionLoaded = ::onNewPortionLoaded,
        scope = screenModelScope,
        startPosition = 0,
    )

    init {
        pagination.startLoading()
    }

    fun onNewPortionLoaded(data: List<Searchable>) {
        println(data)
    }
}
