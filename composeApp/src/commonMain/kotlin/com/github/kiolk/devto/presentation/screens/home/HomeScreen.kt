package com.github.kiolk.devto.presentation.screens.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.kiolk.devto.presentation.screens.article.ArticleScreen
import com.github.kiolk.devto.presentation.views.article.ArticleItem

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<HomeScreenModel>()

        val articlesState by screenModel.articlesState.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LazyColumn {
            items(articlesState.size) { articleIndex ->
                ArticleItem(articlesState[articleIndex]) {
                    navigator.push(ArticleScreen())
                }
            }
        }
    }
}
