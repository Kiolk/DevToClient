package com.github.kiolk.devto.presentation.screens.article

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.github.kiolk.devto.presentation.screens.tag.StubWebScreen
import org.koin.core.parameter.parametersOf

class ArticleScreen(
    private val userName: String,
    private val slug: String,
    private val articleId: String,
    private val commentId: String? = null,
    private val showComments: Boolean = false,
) : Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<ArticleScreenModel>(parameters = { parametersOf(articleId, commentId, showComments) })

        StubWebScreen("https://dev.to/$userName/$slug")
    }
}
