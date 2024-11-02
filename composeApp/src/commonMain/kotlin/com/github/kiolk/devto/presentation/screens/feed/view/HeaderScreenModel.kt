package com.github.kiolk.devto.presentation.screens.feed.view

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.github.kiolk.devto.domain.usecases.GetUserByIdUseCase
import com.github.kiolk.devto.presentation.screens.search.mapper.mapToUserUi
import com.github.kiolk.devto.presentation.screens.search.model.SearchableUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HeaderScreenModel(params: FeedParameter, private val getUserByIdUseCase: GetUserByIdUseCase) :
    ScreenModel {

    private val _searchableItem: MutableStateFlow<SearchableUi> =
        MutableStateFlow(object : SearchableUi {})
    val searchableItem: StateFlow<SearchableUi> = _searchableItem

    init {
        when (params) {
            is FeedParameter.Tag -> TODO()
            is FeedParameter.User -> {
                screenModelScope.launch {
                    val user = getUserByIdUseCase(params.userId)
                    _searchableItem.value = user.mapToUserUi()
                }
            }
        }
    }
}
