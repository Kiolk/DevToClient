package com.github.kiolk.devto.presentation.screens.views

import Organization
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.kiolk.devto.presentation.models.Article
import com.github.kiolk.devto.presentation.models.FlareTag
import com.github.kiolk.devto.presentation.models.User

@Composable
fun ArticleItem(article: Article) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        shape = RoundedCornerShape(4.dp),
        backgroundColor = Color.White,
        elevation = 2.dp,
        border = BorderStroke(0.5.dp, Color.LightGray),
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            UserOrganisationAvatar(article.user, article.organization)
            Column(modifier = Modifier.padding(start = 2.dp)) {
                Row {
                    Column {
                        UserNameWithOrganisation(article.user, article.organization)
                        PublicationDate(article)
                    }
                }
                Text(article.title)
                ArticleTags(article.tagList, article.flareTag)
                Row {
                    Reactions(article)
                    CommentsButton(article)
                    ReadingTime(article.readingTimeMinutes)
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
fun PublicationDate(article: Article) {
}

@Composable
fun UserNameWithOrganisation(user: User, organization: Organization?) {
    Row {
        Text(user.name)
        if (organization != null) {
            Text(" for ${organization.name}")
        }
    }
}

