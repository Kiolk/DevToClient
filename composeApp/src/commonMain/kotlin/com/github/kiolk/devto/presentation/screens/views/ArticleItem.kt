package com.github.kiolk.devto.presentation.screens.views

import Organization
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.kiolk.devto.presentation.models.Article
import com.github.kiolk.devto.presentation.models.FlareTag
import com.github.kiolk.devto.presentation.models.User
import com.github.kiolk.devto.presentation.screens.models.ArticleUi

@Composable
fun ArticleItem(articleUi: ArticleUi) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        shape = RoundedCornerShape(4.dp),
        backgroundColor = Color.White,
        elevation = 2.dp,
        border = BorderStroke(0.5.dp, Color.LightGray),
    ) {
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
                        PublicationDate(articleUi)
                    }
                }
                Text(articleUi.article.title)
                ArticleTags(articleUi.article.tagList, articleUi.article.flareTag)
                Row {
                    Reactions(articleUi.article)
                    CommentsButton(articleUi.article)
                    ReadingTime(articleUi.article.readingTimeMinutes)
                }
            }
        }
    }
}

@Composable
fun ReadingTime(readingTimeMinutes: Int) {
}

@Composable
fun CommentsButton(article: Article) {
}

@Composable
fun Reactions(article: Article) {
}

@Composable
fun ArticleTags(tagList: List<String>, flareTag: FlareTag?) {
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

