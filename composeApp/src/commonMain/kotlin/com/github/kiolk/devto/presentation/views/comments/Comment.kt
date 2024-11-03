package com.github.kiolk.devto.presentation.views.comments

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.github.kiolk.devto.presentation.screens.home.models.CommentUi
import com.github.kiolk.devto.presentation.screens.webView.WebContent

@Composable
fun Comment(
    commentUi: CommentUi,
    level: Int = 0,
    onUserClick: (userId: Int) -> Unit = {},
    onCommentClick: (commentId: Int) -> Unit = {},
) {
    Column(modifier = Modifier.padding(top = 8.dp)) {
        Row {
            Box(modifier = Modifier.width(10.dp * level))
            Column {
                AsyncImage(
                    model = commentUi.userProfileImage,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .clickable { onUserClick(commentUi.userId) }
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Card {
                Column {
                    Row {
                        Text(commentUi.userName)
                    }
                    WebContent(
                        html = commentUi.text,
                        maxLines = 100,
                    ) {
                        onCommentClick(commentUi.id.toIntOrNull() ?: 0)
                    }
                }
            }
        }
        commentUi.children.forEach {
            Comment(
                commentUi = it,
                level = level + 1,
                onUserClick = onUserClick,
                onCommentClick = onCommentClick,
            )
        }
    }
}
