package com.github.kiolk.devto

import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.navigator.Navigator
import com.github.kiolk.devto.presentation.screens.ArticlesScreen

fun MainViewController() = ComposeUIViewController {

    Navigator(ArticlesScreen())
}
