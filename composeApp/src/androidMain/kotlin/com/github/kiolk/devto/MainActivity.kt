package com.github.kiolk.devto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.navigator.Navigator
import com.github.kiolk.devto.domain.usecases.GetAppThemeUseCase
import com.github.kiolk.devto.presentation.screens.main.MainScreen
import com.github.kiolk.devto.presentation.theme.DevToTheme
import com.github.kiolk.devto.utils.theme.ThemeChangeReceiver
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val getAppThemeUseCase: GetAppThemeUseCase by inject()
    private val themeChangeReceiver: ThemeChangeReceiver by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        themeChangeReceiver.register(this)

        setContent {
            val isDark by getAppThemeUseCase().collectAsState(false)
            DevToTheme(isDarkTheme = isDark) {
                Navigator(MainScreen())
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        themeChangeReceiver.unregister(this)
    }
}
