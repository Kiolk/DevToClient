package com.github.kiolk.devto.presentation.screens.home

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.github.kiolk.devto.domain.usecases.GetArticleUseCase
import com.github.kiolk.devto.presentation.models.Article
import com.github.kiolk.devto.presentation.models.GetArticlesParams
import com.github.kiolk.devto.presentation.screens.home.mappers.mapToArticleUi
import com.github.kiolk.devto.presentation.screens.home.models.ArticleUi
import com.github.kiolk.devto.utils.localisation.StringProvider
import com.github.kiolk.devto.utils.pagination.Pagination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenModel(
    private val getArticlesUseCase: GetArticleUseCase,
    private val stringProvider: StringProvider
) : ScreenModel {

    private var _articlesState: MutableStateFlow<List<ArticleUi>> =
        MutableStateFlow(emptyList())
    val articlesState = _articlesState.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val pagination: Pagination<Article> = Pagination(
        source = ::loadNext,
        onNewPortionLoaded = ::onNewPortionLoaded,
        scope = screenModelScope,
    )

    init {
        pagination.startLoading()
    }

    private suspend fun loadNext(page: Int): List<Article> {
        return getArticlesUseCase(GetArticlesParams(page = page))
    }

    private fun onNewPortionLoaded(newPortion: List<Article>) {
        _isLoading.value = false
        _articlesState.value += newPortion.map { it.mapToArticleUi(stringProvider) }
    }

    fun onBookmarkClick(article: ArticleUi) {
        screenModelScope.launch {
            //TODO add logic for storing bookmarks
            val index = _articlesState.value.indexOfFirst { it.article.id == article.article.id }
            val new = mutableListOf<ArticleUi>()
            articlesState.value.forEach {
                if (it.article.id == article.article.id) {
                    new.add(it.copy(isBookmarked = !article.isBookmarked))
                } else {
                    new.add(it)
                }
            }
            _articlesState.value = new
        }
    }

    fun loadMoreArticles() {
        _isLoading.value = true
        pagination.loadNewPortion()
    }
}
