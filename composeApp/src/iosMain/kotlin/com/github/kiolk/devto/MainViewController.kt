package com.github.kiolk.devto

import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.navigator.Navigator
import com.github.kiolk.devto.presentation.screens.main.MainScreen
import com.github.kiolk.devto.presentation.theme.DevToTheme

fun MainViewController() = ComposeUIViewController {

    DevToTheme(isDarkTheme = false) {
        Navigator(MainScreen())
    }
}
