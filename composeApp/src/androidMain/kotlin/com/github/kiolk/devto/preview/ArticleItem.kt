package com.github.kiolk.devto.preview

import Organization
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.github.kiolk.devto.presentation.models.Article
import com.github.kiolk.devto.presentation.models.User
import com.github.kiolk.devto.presentation.screens.home.mappers.mapToArticleUi
import com.github.kiolk.devto.presentation.theme.DevToTheme
import com.github.kiolk.devto.presentation.views.article.ArticleItem
import kotlinx.datetime.Clock

@Preview(showBackground = true, device = Devices.DEFAULT, showSystemUi = true)
@Composable
fun ArticleItemPreview() {
    DevToTheme(isDarkTheme = true) {
        ArticleItem(fakeArticle.mapToArticleUi(MockStringProvider), MockStringProvider)
    }
}

val fakeArticle = Article(
    1, "", "Test", "Description", Clock.System.now(), user = User(
        name = "User",
        username = "User name",
        twitterUsername = null,
        githubUsername = null,
        websiteUrl = "",
        profileImage = "https://picsum.photos/200/300",
        profileImage90 = "https://picsum.photos/200/300",
    ), organization = Organization(
        name = "Daresay",
        username = "Daresay AB",
        slug = "We work for future",
        profileImage = "https://picsum.photos/200/300",
        profileImage90 = "https://picsum.photos/200/300"
    ),
    commentsCount = 0,
    publicReactionCount = 0,
    positiveReactionCount = 0,
    coverImage = "",
    readingTimeMinutes = 3,
    tagList = listOf("Development", "VoiceRecognition", "Android", "Kotlin"),
    flareTag = null
)
