package com.github.kiolk.devto.presentation.screens.webView

import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
actual fun WebViewScreen(url: String) {
    AndroidView(factory = {
        WebView(it).apply {
            webViewClient = WebViewClient()
            loadUrl(url)
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                settings.setAlgorithmicDarkeningAllowed(true)
            } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                settings.forceDark = WebSettings.FORCE_DARK_ON
            }
        }
    })
}

@Composable
actual fun WebContent(html: String, function: () -> Unit) {
    //TODO Need modify to display correctly content for different themes https://github.com/Kiolk/DevToClient/issues/2
    MarkdownText(
        markdown = html,
        modifier = Modifier.padding(8.dp),
        maxLines = 3,
        style = TextStyle(
            color = MaterialTheme.colors.onSurface,
        ),
    )
}
