package com.github.kiolk.devto.presentation.views.article

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import com.github.kiolk.devto.domain.models.Organization
import com.github.kiolk.devto.domain.models.User
import com.github.kiolk.devto.presentation.models.FlareTag
import com.github.kiolk.devto.presentation.screens.home.models.ArticleUi
import com.github.kiolk.devto.presentation.screens.home.models.CommentUi
import com.github.kiolk.devto.presentation.screens.home.models.TagUi
import com.github.kiolk.devto.presentation.screens.tag.TagScreen
import com.github.kiolk.devto.presentation.screens.user.UserScreen
import com.github.kiolk.devto.presentation.screens.webView.WebContent
import com.github.kiolk.devto.presentation.views.avatar.UserOrganisationAvatar
import com.github.kiolk.devto.presentation.views.buttons.bookMark.BookMarkButton
import com.github.kiolk.devto.presentation.views.buttons.comment.CommentsButton
import com.github.kiolk.devto.presentation.views.buttons.reaction.ReactionsButton
import com.github.kiolk.devto.presentation.views.tag.Tag
import com.github.kiolk.devto.utils.localisation.StringProvider
import com.github.kiolk.devto.utils.localisation.StringsKeys

@Composable
fun ArticleItem(
    articleUi: ArticleUi,
    stringProvider: StringProvider,
    onArticleClick: (articleUi: ArticleUi, commentId: String?, showComments: Boolean) -> Unit = { _, _, _ -> },
    onBookmarkClick: (articleUi: ArticleUi) -> Unit = {},
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = 2.dp,
        border = BorderStroke(0.5.dp, Color.LightGray),
    ) {
        val navigator = LocalNavigator.currentOrThrow

        Column {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.Top) {
                UserOrganisationAvatar(
                    articleUi.article.user,
                    articleUi.article.organization,
                    onUserClick = { navigator.push(UserScreen(it)) }
                )
                Column(
                    modifier = Modifier.padding(start = 2.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Row {
                        Column(verticalArrangement = Arrangement.Top) {
                            UserNameWithOrganisation(
                                articleUi.article.user,
                                articleUi.article.organization,
                                onUserClick = { navigator.push(UserScreen(it)) },
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                PublicationDate(articleUi)
                                Spacer(Modifier.weight(1f))
                                ReadingTime(articleUi.article.readingTimeMinutes, stringProvider)
                            }
                        }
                    }
                    ArticleTitle(articleUi) { onArticleClick(it, null, false) }
                    ArticleTags(articleUi.tags, articleUi.article.flareTag, {
//                        navigator.push(
//                            TagScreen(it.toTa)
//                        )
                    })
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ReactionsButton(articleUi, stringProvider) {
                            onArticleClick(
                                it,
                                null,
                                false
                            )
                        }
                        CommentsButton(articleUi,
                            stringProvider = stringProvider,
                            onCommentsClick = { onArticleClick(articleUi, null, true) })
                        Spacer(Modifier.weight(1f))
                        BookMarkButton(articleUi, onBookmarkClick)
                    }
                }
            }
            /**
             * TODO Need find more efficient way to display comments with web formatted text. More information here https://github.com/Kiolk/DevToClient/issues/2
             */
            CommentsBlock(
                articleUi,
                stringProvider = stringProvider,
                onCommentClick = { comment ->
                    onArticleClick(
                        articleUi,
                        comment.id.toString(),
                        true
                    )
                },
                onSeeAllCommentsClick = { onArticleClick(articleUi, null, true) },
                onUserClick = { navigator.push(UserScreen(it)) }
            )
        }
    }
}

@Composable
fun ReadingTime(readingTimeMinutes: Int, stringProvider: StringProvider) {
    Text(
        text = stringProvider.getString(StringsKeys.READING_TIME, readingTimeMinutes),
        color = Color.LightGray,
        style = MaterialTheme.typography.overline,
    )
}

@Composable
fun CommentsBlock(
    articleUi: ArticleUi,
    onCommentClick: (commentUi: CommentUi) -> Unit,
    onSeeAllCommentsClick: () -> Unit,
    stringProvider: StringProvider,
    onUserClick: (userName: String) -> Unit
) {
    val selectedTopComments = articleUi.topComments.filter { it.text.length > 40 }.take(2)

    if (selectedTopComments.isEmpty()) {
        return
    }

    selectedTopComments.forEach {
        Comment(it, onCommentClick = onCommentClick, onUserClick = onUserClick)
    }

    if (articleUi.numberOfComments > 2) {
        Text(
            stringProvider.getString(StringsKeys.READING_ALL_COMMENTS, articleUi.numberOfComments),
            modifier = Modifier.padding(start = 48.dp, end = 8.dp, bottom = 8.dp)
                .clickable { onSeeAllCommentsClick() },
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
fun Comment(
    commentUi: CommentUi,
    onCommentClick: (commentUi: CommentUi) -> Unit,
    size: Dp = 35.dp,
    onUserClick: (user: String) -> Unit
) {
    Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp).fillMaxWidth()) {
        Box(modifier = Modifier.size(size)) {
            Box(
                modifier = Modifier.size(size - 15.dp)
                    .background(MaterialTheme.colors.surface, CircleShape)
                    .border(1.dp, Color.LightGray, CircleShape).align(Alignment.Center)
            ) {
                AsyncImage(
                    model = commentUi.userProfileImage, // replace with working URL
                    contentDescription = null,
                    modifier = Modifier
                        .size(size - 16.dp)
                        .align(Alignment.Center).clip(CircleShape)
                        .clickable { onUserClick(commentUi.userName) }
                )
            }
        }
        Box(
            modifier = Modifier.background(
                shape = RoundedCornerShape(4.dp),
                color = MaterialTheme.colors.surface
            ).fillMaxWidth().clickable { onCommentClick(commentUi) }
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Row(
                    modifier = Modifier
                ) {
                    Text(
                        commentUi.userName,
                        style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.clickable { onUserClick(commentUi.userName) }
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        commentUi.commentTime,
                        style = MaterialTheme.typography.overline.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                WebContent(commentUi.text) { onCommentClick(commentUi) }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalLayoutApi::class)
@Composable
fun ArticleTags(tagList: List<TagUi>, flareTag: FlareTag?, onTagClick: (tagUi: TagUi) -> Unit) {
    FlowRow(
        modifier = Modifier.wrapContentHeight().padding(0.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp, alignment = Alignment.Top)
    ) {

        for (tag in tagList) {
            Tag(tag, onTagClick)
        }
    }
}

@Composable
fun ArticleTitle(articleUi: ArticleUi, onArticleClick: (articleUi: ArticleUi) -> Unit) {
    val interactionSource = remember { MutableInteractionSource() }
    val isFocused: Boolean by interactionSource.collectIsFocusedAsState()
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()

    Text(
        articleUi.article.title,
        color = when {
            isPressed -> Color.Blue
            isFocused -> Color.Blue
            else -> MaterialTheme.typography.h6.color
        },
        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
        modifier = Modifier.clickable(interactionSource = interactionSource, indication = null) {
            onArticleClick(articleUi)
        }.focusable(interactionSource = interactionSource)
    )
}

@Composable
fun PublicationDate(articleUi: ArticleUi) {
    Text(
        "${articleUi.publishedAt} ${articleUi.publishedAgo}".trim(),
        color = Color.LightGray,
        style = MaterialTheme.typography.overline,
    )
}

@Composable
fun UserNameWithOrganisation(
    user: User,
    organization: Organization?,
    onUserClick: (userName: String) -> Unit
) {
    Row(verticalAlignment = Alignment.Top) {
        Text(text = user.name,
            style = MaterialTheme.typography.caption,
            modifier = Modifier.clickable { onUserClick(user.name) })
        if (organization != null) {
            Text(" for ", color = Color.LightGray, style = MaterialTheme.typography.caption)
            Text(organization.name, style = MaterialTheme.typography.caption)
        }
    }
}
