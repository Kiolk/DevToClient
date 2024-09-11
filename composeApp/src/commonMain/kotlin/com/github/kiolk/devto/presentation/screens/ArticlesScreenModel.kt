package com.github.kiolk.devto.presentation.screens

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.github.kiolk.devto.domain.usecases.GetArticleUseCase
import com.github.kiolk.devto.presentation.models.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ArticlesScreenModel(private val getArticlesUseCase: GetArticleUseCase) : ScreenModel {

    init {
        loadArticles()
    }

    private fun loadArticles() {
        screenModelScope.launch {
            getArticlesUseCase().collect {
                _articlesState.value = it
            }
        }
    }

    private var _articlesState: MutableStateFlow<List<Article>> =
        MutableStateFlow(emptyList())
    val articlesState = _articlesState.asStateFlow()
}
