package com.github.kiolk.devto.presentation.screens.webView

import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
actual fun WebViewScreen(url: String) {
    Text(url)
}

@Composable
actual fun WebContent(html: String, function: () -> Unit) {
//TODO Need implement logic for this issue https://github.com/Kiolk/DevToClient/issues/2
    Text(html)
}

