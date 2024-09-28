package com.github.kiolk.devto.presentation.screens.article

import cafe.adriel.voyager.core.model.ScreenModel
import com.github.kiolk.devto.presentation.screens.home.models.ArticleUi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ArticleScreenModel(articleId: String, commentId: String = "", seeComments: Boolean = false) : ScreenModel {

    private val _articleUi: MutableStateFlow<ArticleUi?> = MutableStateFlow(null)
    val articleUi: StateFlow<ArticleUi?> = _articleUi
}
