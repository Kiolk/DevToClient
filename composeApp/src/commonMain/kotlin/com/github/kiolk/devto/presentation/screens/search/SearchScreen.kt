package com.github.kiolk.devto.presentation.screens.search

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel

class SearchScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel: SearchScreenModel = koinScreenModel<SearchScreenModel>()
        screenModel.pagination
    }
}
