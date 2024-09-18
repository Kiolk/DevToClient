package com.github.kiolk.devto.presentation.screens.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.github.kiolk.devto.domain.usecases.GetArticleUseCase
import com.github.kiolk.devto.presentation.models.GetArticlesParams
import com.github.kiolk.devto.presentation.screens.home.mappers.mapToArticleUi
import com.github.kiolk.devto.presentation.screens.home.models.ArticleUi
import com.github.kiolk.devto.utils.localisation.StringProvider
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenModel(private val getArticlesUseCase: GetArticleUseCase, private val stringProvider: StringProvider) : ScreenModel {

    init {
        loadArticles()
    }

    private fun loadArticles() {
        screenModelScope.launch {
            for (i in 0..5) {
                delay(1000)
                getArticlesUseCase(GetArticlesParams(page = i)).collect {
                    _articlesState.value += it.map { it.mapToArticleUi(stringProvider) }
                }
            }
        }
    }

    private var _articlesState: MutableStateFlow<List<ArticleUi>> =
        MutableStateFlow(emptyList())
    val articlesState = _articlesState.asStateFlow()
}
