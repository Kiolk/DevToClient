package com.github.kiolk.devto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import cafe.adriel.voyager.navigator.Navigator
import com.github.kiolk.devto.presentation.screens.main.MainScreen
import com.github.kiolk.devto.presentation.theme.DevToTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DevToTheme(isDarkTheme = true) {
                Navigator(MainScreen())
            }
        }
    }
}
