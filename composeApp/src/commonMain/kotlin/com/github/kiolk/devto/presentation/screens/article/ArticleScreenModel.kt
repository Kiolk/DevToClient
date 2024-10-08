package com.github.kiolk.devto.presentation.screens.article

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.github.kiolk.devto.domain.usecases.GetArticleByIdUseCase
import com.github.kiolk.devto.presentation.screens.home.mappers.mapToArticleUi
import com.github.kiolk.devto.presentation.screens.home.models.ArticleUi
import com.github.kiolk.devto.utils.localisation.StringProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ArticleScreenModel(
    private val articleId: String,
    private val commentId: String = "",
    private val seeComments: Boolean = false,
    private val getArticleByIdUseCase: GetArticleByIdUseCase,
    private val stringProvider: StringProvider,
) : ScreenModel {

    private val _articleUi: MutableStateFlow<ArticleUi?> = MutableStateFlow(null)
    val articleUi: StateFlow<ArticleUi?> = _articleUi

    init {
        loadArticle()
    }

    private fun loadArticle() {
        screenModelScope.launch {
            val article: ArticleUi = getArticleByIdUseCase(articleId = articleId.toInt()).mapToArticleUi(stringProvider)
            println(article.toString())
        }
    }
}

