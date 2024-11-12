package com.github.kiolk.devto

import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.navigator.Navigator
import com.github.kiolk.devto.presentation.screens.main.MainScreen
import com.github.kiolk.devto.presentation.theme.DevToTheme
import com.github.kiolk.devto.utils.theme.ThemeHelper
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewController : KoinComponent {
    private val themeHelper: ThemeHelper by inject()

    fun create() = ComposeUIViewController {
        val isDark = themeHelper.isDarkTheme()
        DevToTheme(isDarkTheme = isDark) {
            Navigator(MainScreen())
        }
    }
}
