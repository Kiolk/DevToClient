package com.github.kiolk.devto.presentation.screens.comments

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.github.kiolk.devto.domain.usecases.GetCommentByIdUseCase
import com.github.kiolk.devto.presentation.screens.home.mappers.toCommentUi
import com.github.kiolk.devto.presentation.screens.home.models.CommentUi
import com.github.kiolk.devto.utils.localisation.StringProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CommentsScreenModel(
    private val commentId: String,
    private val getCommentByIdUseCase: GetCommentByIdUseCase,
    private val stringProvider: StringProvider
) : ScreenModel {

    private val _commentsUi: MutableStateFlow<List<CommentUi>> = MutableStateFlow(emptyList())
    val commentsUi: StateFlow<List<CommentUi>> = _commentsUi

    private val _totalComments: MutableStateFlow<Int> = MutableStateFlow(0)
    val totalComments: StateFlow<Int> = _totalComments

    init {
        screenModelScope.launch {
            val comment = getCommentByIdUseCase(commentId)
            _commentsUi.value = listOf(comment.toCommentUi(stringProvider))
            _totalComments.value = _commentsUi.value.sumOf { it.replies() + 1 }
        }
    }
}
