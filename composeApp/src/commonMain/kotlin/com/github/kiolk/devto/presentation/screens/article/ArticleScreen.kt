package com.github.kiolk.devto.presentation.screens.article

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import org.koin.core.parameter.parametersOf

class ArticleScreen(private val openArticlesParams: OpenArticleParams) : Screen {
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<ArticleScreenModel>(parameters = { parametersOf(openArticlesParams) })

        val article by screenModel.articleUi.collectAsState()

        article?.title?.let {
            Text(it)
        }
    }
}
