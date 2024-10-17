package com.github.kiolk.devto.presentation.screens.search.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.github.kiolk.devto.presentation.screens.search.model.CommentSearchUi

@Composable
fun CommentSearchCard(comment: CommentSearchUi) {
    Text(comment.comment.text)
}
