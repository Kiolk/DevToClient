package com.github.kiolk.devto

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.navigator.Navigator
import com.github.kiolk.devto.domain.usecases.GetAppThemeUseCase
import com.github.kiolk.devto.presentation.screens.main.MainScreen
import com.github.kiolk.devto.presentation.theme.DevToTheme
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class MainViewController : KoinComponent {
    private val getAppThemeUseCase: GetAppThemeUseCase by inject()

    fun create() = ComposeUIViewController {
        val isDark by getAppThemeUseCase().collectAsState(false)
        DevToTheme(isDarkTheme = isDark) {
            Navigator(MainScreen())
        }
    }
}
