package com.github.kiolk.devto.presentation.screens.search.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.github.kiolk.devto.domain.models.Comment
import com.github.kiolk.devto.domain.models.User
import com.github.kiolk.devto.presentation.screens.search.mapper.mapToUser
import com.github.kiolk.devto.presentation.screens.search.model.CommentSearchUi
import com.github.kiolk.devto.presentation.screens.webView.WebContent

@Composable
fun CommentSearchCard(
    comment: CommentSearchUi,
    size: Dp = 40.dp,
    onUserClick: (User) -> Unit = {},
    onCommentClick: (Comment) -> Unit = {},
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable {
            onCommentClick(comment.comment)
        },
        shape = RoundedCornerShape(4.dp),
        elevation = 2.dp,
        border = BorderStroke(0.5.dp, Color.LightGray),
    ) {
        Row(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp, top = 16.dp)
                .fillMaxWidth()
        ) {
            Box(modifier = Modifier.size(size)) {
                Box(
                    modifier = Modifier.size(size - 15.dp)
                        .background(MaterialTheme.colors.surface, CircleShape)
                        .border(1.dp, Color.LightGray, CircleShape).align(Alignment.Center)
                ) {
                    AsyncImage(
                        model = comment.comment.profileImage90, // replace with working URL
                        contentDescription = null,
                        modifier = Modifier
                            .size(size - 16.dp)
                            .align(Alignment.Center).clip(CircleShape)
                            .clickable { onUserClick(comment.comment.mapToUser()) }
                    )
                }
            }
            Box(
                modifier = Modifier
                    .clickable { onCommentClick(comment.comment) }
                    .background(
                        shape = RoundedCornerShape(4.dp),
                        color = MaterialTheme.colors.surface
                    ).fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(comment.articleTitle, maxLines = 1)
                    Row(
                        modifier = Modifier
                    ) {
                        Text(
                            comment.comment.username,
                            style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold),
                            modifier = Modifier.clickable { onUserClick(comment.comment.mapToUser()) }
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = comment.publishedAt,
                            style = MaterialTheme.typography.overline.copy(
                                fontWeight = FontWeight.Bold,
                            )
                        )
                    }
                    WebContent(
                        comment.comment.text,
                        maxLines = 100,
                    ) { onCommentClick(comment.comment) }
                }
            }
        }
    }
}
