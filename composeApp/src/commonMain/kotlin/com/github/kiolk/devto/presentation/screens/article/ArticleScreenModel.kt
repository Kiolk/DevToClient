package com.github.kiolk.devto.presentation.screens.article

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.github.kiolk.devto.domain.usecases.GetArticleByIdUseCase
import com.github.kiolk.devto.domain.usecases.GetArticleByTitleUseCase
import com.github.kiolk.devto.presentation.screens.home.mappers.mapToArticleUi
import com.github.kiolk.devto.presentation.screens.home.models.ArticleUi
import com.github.kiolk.devto.utils.localisation.StringProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

sealed class OpenArticleParams {
    data class OpenById(val articleId: Int) : OpenArticleParams()
    data class OpenByTitle(val title: String, val commentId: String? = null) : OpenArticleParams()
}

class ArticleScreenModel(
    private val openArticleParams: OpenArticleParams,
    private val getArticleByIdUseCase: GetArticleByIdUseCase,
    private val getArticleByTitleUseCase: GetArticleByTitleUseCase,
    private val stringProvider: StringProvider,
) : ScreenModel {

    private val _articleUi: MutableStateFlow<ArticleUi?> = MutableStateFlow(null)
    val articleUi: StateFlow<ArticleUi?> = _articleUi

    init {
        loadArticle()
    }

    private fun loadArticle() {
        screenModelScope.launch {
            val article: ArticleUi = when (openArticleParams) {
                is OpenArticleParams.OpenById -> {
                    getArticleByIdUseCase(articleId = openArticleParams.articleId).mapToArticleUi(
                        stringProvider
                    )
                }

                is OpenArticleParams.OpenByTitle -> {
                    getArticleByTitleUseCase(title = openArticleParams.title).mapToArticleUi(
                        stringProvider
                    )
                }
            }
            _articleUi.value = article
        }
    }
}

