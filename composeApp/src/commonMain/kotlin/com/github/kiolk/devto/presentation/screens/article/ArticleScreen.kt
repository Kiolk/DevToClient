package com.github.kiolk.devto.presentation.screens.article

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import com.github.kiolk.devto.presentation.screens.tag.StubWebScreen

class ArticleScreen(private val userName: String, private val slug: String) : Screen {
    @Composable
    override fun Content() {
        StubWebScreen("https://dev.to/$userName/$slug")
    }
}
