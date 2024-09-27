package com.github.kiolk.devto.presentation.views.article

import Organization
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
import coil3.compose.AsyncImage
import com.github.kiolk.devto.presentation.models.FlareTag
import com.github.kiolk.devto.presentation.models.User
import com.github.kiolk.devto.presentation.screens.home.models.ArticleUi
import com.github.kiolk.devto.presentation.screens.home.models.CommentUi
import com.github.kiolk.devto.presentation.screens.home.models.TagUi
import com.github.kiolk.devto.presentation.screens.webView.WebContent
import com.github.kiolk.devto.presentation.views.avatar.UserOrganisationAvatar
import com.github.kiolk.devto.presentation.views.buttons.bookMark.BookMarkButton
import com.github.kiolk.devto.presentation.views.buttons.comment.CommentsButton
import com.github.kiolk.devto.presentation.views.buttons.reaction.ReactionsButton
import com.github.kiolk.devto.presentation.views.tag.Tag
import com.github.kiolk.devto.utils.colors.DevToColors
import com.github.kiolk.devto.utils.localisation.StringProvider
import com.github.kiolk.devto.utils.localisation.StringsKeys

@Composable
fun ArticleItem(
    articleUi: ArticleUi,
    stringProvider: StringProvider,
    onArticleClick: (articleUi: ArticleUi) -> Unit = {},
    onTagClick: (tagUi: TagUi) -> Unit = {},
    onBookmarkClick: (articleUi: ArticleUi) -> Unit = {}
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = 2.dp,
        border = BorderStroke(0.5.dp, Color.LightGray),
    ) {
        Column {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.Top) {
                UserOrganisationAvatar(articleUi.article.user, articleUi.article.organization)
                Column(
                    modifier = Modifier.padding(start = 2.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Row {
                        Column(verticalArrangement = Arrangement.Top) {
                            UserNameWithOrganisation(
                                articleUi.article.user,
                                articleUi.article.organization
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                PublicationDate(articleUi)
                                Spacer(Modifier.weight(1f))
                                ReadingTime(articleUi.article.readingTimeMinutes, stringProvider)
                            }
                        }
                    }
                    ArticleTitle(articleUi, onArticleClick)
                    ArticleTags(articleUi.tags, articleUi.article.flareTag, onTagClick)
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ReactionsButton(articleUi, stringProvider, onArticleClick)
                        CommentsButton(articleUi, stringProvider = stringProvider)
                        Spacer(Modifier.weight(1f))
                        BookMarkButton(articleUi, onBookmarkClick)
                    }
                }
            }
            articleUi.topComments.firstOrNull { it.text.length > 40 }?.let { comment ->
                Comment(comment, onCommentClick = {})
            }
        }
    }
}

@Composable
fun ReadingTime(readingTimeMinutes: Int, stringProvider: StringProvider) {
    Text(
        text = stringProvider.getFormattedString(StringsKeys.READING_TIME, readingTimeMinutes),
        color = Color.LightGray,
        style = MaterialTheme.typography.overline,
    )
}

@Composable
fun Comment(commentUi: CommentUi, onCommentClick: (commentUi: CommentUi) -> Unit, size: Dp = 35.dp) {
    Row(modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp).fillMaxWidth()) {
        Box(modifier = Modifier.size(size)) {
            Box(
                modifier = Modifier.size(size - 15.dp).background(Color.LightGray, CircleShape)
                    .border(1.dp, Color.LightGray, CircleShape).align(Alignment.Center)
            ) {
                AsyncImage(
                    model = commentUi.userProfileImage, // replace with working URL
                    contentDescription = null,
                    modifier = Modifier
                        .size(size - 16.dp)
                        .align(Alignment.Center)
                        .clip(CircleShape)
                )
            }
        }
        Box(
            modifier = Modifier.background(shape = RoundedCornerShape(4.dp), color = DevToColors.lightGray).fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(8.dp)) {
                Row(modifier = Modifier) {
                    Text(commentUi.userName, style = MaterialTheme.typography.caption.copy(fontWeight = FontWeight.Bold))
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        commentUi.commentTime,
                        style = MaterialTheme.typography.overline.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
                WebContent(commentUi.text)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalLayoutApi::class)
@Composable
fun ArticleTags(tagList: List<TagUi>, flareTag: FlareTag?, onTagClick: (tagUi: TagUi) -> Unit) {
    FlowRow(
        modifier = Modifier
            .wrapContentHeight()
            .padding(0.dp),
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
fun UserNameWithOrganisation(user: User, organization: Organization?) {
    Row(verticalAlignment = Alignment.Top) {
        Text(text = user.name, style = MaterialTheme.typography.caption)
        if (organization != null) {
            Text(" for ", color = Color.LightGray, style = MaterialTheme.typography.caption)
            Text(organization.name, style = MaterialTheme.typography.caption)
        }
    }
}

