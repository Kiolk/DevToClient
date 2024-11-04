package com.github.kiolk.devto.presentation.views.comments

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.github.kiolk.devto.presentation.screens.home.models.CommentUi
import com.github.kiolk.devto.presentation.screens.webView.WebContent
import com.github.kiolk.devto.utils.colors.DevToColors
import devto.composeapp.generated.resources.Res
import devto.composeapp.generated.resources.ic_collaps
import devto.composeapp.generated.resources.ic_expand
import org.jetbrains.compose.resources.painterResource

@Composable
fun Comment(
    commentUi: CommentUi,
    level: Int = 0,
    onUserClick: (userId: Int) -> Unit = {},
    onCommentClick: (commentId: Int) -> Unit = {},
) {
    val isExpanded = remember { mutableStateOf(true) }

    if (!isExpanded.value) {
        Row(modifier = Modifier.padding(top = 8.dp)) {
            Spacer(level)
            Row(
                modifier = Modifier.fillMaxWidth()
                    .background(shape = RoundedCornerShape(4.dp), color = DevToColors.lightGray)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier.size(20.dp).padding(4.dp).clickable {
                        isExpanded.value = true
                    }
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_expand),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
                    )
                }
                Text(commentUi.name)
            }
        }
        return
    }

    Column(modifier = Modifier.padding(top = 8.dp)) {
        Row {
            Spacer(level)
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                AsyncImage(
                    model = commentUi.userProfileImage,
                    contentDescription = null,
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .clickable { onUserClick(commentUi.userId) }
                )
                Box(
                    modifier = Modifier.size(20.dp).padding(4.dp).clickable {
                        isExpanded.value = false
                    }
                ) {
                    Image(
                        painter = painterResource(Res.drawable.ic_collaps),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.colors.onSurface)
                    )
                }
            }
            Spacer(modifier = Modifier.width(4.dp))
            Card {
                Column {
                    Row {
                        Text(commentUi.name)
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

@Composable
fun Spacer(level: Int) = Box(modifier = Modifier.width(10.dp * level))
