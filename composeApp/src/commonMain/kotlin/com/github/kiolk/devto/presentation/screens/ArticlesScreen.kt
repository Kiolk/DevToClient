package com.github.kiolk.devto.presentation.screens

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel

class ArticlesScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<ArticlesScreenModel>()

        val articlesState by screenModel.articlesState.collectAsState()

        Text("Articles: ${articlesState.size}")
    }
}
