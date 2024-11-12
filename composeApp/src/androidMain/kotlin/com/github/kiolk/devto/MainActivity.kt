package com.github.kiolk.devto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import com.github.kiolk.devto.presentation.screens.main.MainScreen
import com.github.kiolk.devto.presentation.theme.DevToTheme
import com.github.kiolk.devto.utils.theme.ThemeHelper
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val themeHelper: ThemeHelper by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val isDark = themeHelper.isDarkTheme()
            DevToTheme(isDarkTheme = isDark) {
                Navigator(MainScreen())
            }
        }
    }
}
