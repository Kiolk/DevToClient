package com.github.kiolk.devto.presentation.screens.webView

import androidx.compose.runtime.Composable

@Composable
expect fun WebViewScreen(url: String)

@Composable
expect fun WebContent(html: String)
