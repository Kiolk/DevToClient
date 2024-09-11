package com.github.kiolk.devto

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import cafe.adriel.voyager.navigator.Navigator
import com.github.kiolk.devto.presentation.screens.ArticlesScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Navigator(ArticlesScreen())
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    App()
}
