package com.github.kiolk.devto.presentation.feed.view

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.github.kiolk.devto.domain.models.Article
import com.github.kiolk.devto.domain.models.Comment
import com.github.kiolk.devto.domain.models.Organization
import com.github.kiolk.devto.domain.models.SearchType
import com.github.kiolk.devto.domain.models.Searchable
import com.github.kiolk.devto.domain.models.Tag
import com.github.kiolk.devto.domain.models.User
import com.github.kiolk.devto.domain.usecases.GetArticleUseCase
import com.github.kiolk.devto.domain.usecases.ToggleReactionUseCase
import com.github.kiolk.devto.presentation.models.GetArticlesParams
import com.github.kiolk.devto.presentation.screens.home.mappers.mapToArticleUi
import com.github.kiolk.devto.presentation.screens.home.models.ArticleUi
import com.github.kiolk.devto.presentation.screens.search.model.SortingTypeUi
import com.github.kiolk.devto.utils.localisation.StringProvider
import com.github.kiolk.devto.utils.pagination.Pagination
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

open class FeedBodyScreenModel(
    private val searchable: Searchable? = null,
    private val getArticlesUseCase: GetArticleUseCase,
    private val stringProvider: StringProvider,
    private val toggleReactionUseCase: ToggleReactionUseCase,
) : ScreenModel {

    val type: SearchType
        get() {
            return when (searchable) {
                is Article -> SearchType.Article
                is Comment -> SearchType.Comment
                is Organization -> SearchType.Organization
                is Searchable -> SearchType.Tag
                is User -> SearchType.User
                null -> SearchType.Article
            }
        }

    private val _feedState: MutableStateFlow<List<ArticleUi>> =
        MutableStateFlow(emptyList())
    val feedState = _feedState.asStateFlow()

    private val _sortingType: MutableStateFlow<SortingTypeUi> =
        MutableStateFlow(SortingTypeUi.Related)
    val sortingType: StateFlow<SortingTypeUi> = _sortingType

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
        return getArticlesUseCase(
            GetArticlesParams(
                page = page,
                sortingType = _sortingType.value.mapToSortingType(),
                type = SearchType.Article,
                tagName = if (searchable is Tag) searchable.name else null
            )
        )
    }

    private fun onNewPortionLoaded(newPortion: List<Article>) {
        _isLoading.value = false
        _feedState.value += newPortion.map { it.mapToArticleUi(stringProvider) }
    }

    fun onBookmarkClick(article: ArticleUi) {
        screenModelScope.launch {
            // TODO add logic for storing bookmarks
            toggleReactionUseCase(
                articleId = article.article.id,
                reactionCategory = "readinglist",
                reactionOn = "Article"
            )
//            val index = _articlesState.value.indexOfFirst { it.article.id == article.article.id }
            val new = mutableListOf<ArticleUi>()
            feedState.value.forEach {
                if (it.article.id == article.article.id) {
                    new.add(it.copy(isBookmarked = !article.isBookmarked))
                } else {
                    new.add(it)
                }
            }
            _feedState.value = new
        }
    }

    fun loadMoreArticles() {
        _isLoading.value = true
        pagination.loadNewPortion()
    }

    fun onSortClick(sortingType: SortingTypeUi) {
        if (_sortingType.value == sortingType) {
            return
        } else {
            _sortingType.value = sortingType
            _feedState.value = emptyList()
            pagination.restart()
        }
    }
}
