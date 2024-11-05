package com.github.kiolk.devto.presentation.views.comments

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.kiolk.devto.presentation.screens.home.models.CommentUi

@Composable
fun CommentsFeed(
    comments: List<CommentUi>,
    totalComments: Int = 0,
    onUserClick: (userId: Int) -> Unit = {},
    onCommentClick: (commentId: Int) -> Unit = {},
) {
    Column(modifier = Modifier.padding(horizontal = 8.dp)) {
        Text("Top comments ($totalComments)")
        comments.forEach {
            Comment(
                commentUi = it,
                level = 0,
                onUserClick,
                onCommentClick,
            )
        }
    }
}
