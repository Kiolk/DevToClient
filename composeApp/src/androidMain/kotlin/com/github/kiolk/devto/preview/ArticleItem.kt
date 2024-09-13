package com.github.kiolk.devto.preview

import Organization
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import com.github.kiolk.devto.presentation.models.Article
import com.github.kiolk.devto.presentation.models.User
import com.github.kiolk.devto.presentation.screens.views.ArticleItem
import kotlinx.datetime.Clock

@Preview(showBackground = true, device = Devices.DEFAULT)
@Composable
fun ArticleItemPreview() {
    ArticleItem(
        Article(
            1, "Test", "Description", Clock.System.now(), user = User(
                name = "User",
                username = "User name",
                twitterUsername = null,
                githubUsername = null,
                userId = 1,
                websiteUrl = "",
                profileImage = "https://picsum.photos/200/300",
                profileImage90 = "https://picsum.photos/200/300",
            ), organization = Organization(
                name = "Daresay",
                username = "Daresay",
                slug = "We work for future",
                profileImage = "https://picsum.photos/200/300",
                profileImage90 = "https://picsum.photos/200/300"
            ),
            commentsCount = 0,
            publicReactionCount = 0,
            positiveReactionCount = 0,
            coverImage = "",
            readingTimeMinutes = 3,
            tagList = emptyList(),
            flareTag = null
        )
    )
}
