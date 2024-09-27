package com.github.kiolk.devto.presentation.screens.webView

import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView

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
actual fun WebContent(html: String) {
    //TODO Need modify to display correctly content for different themes https://github.com/Kiolk/DevToClient/issues/2
    AndroidView(
        factory = { context ->
            WebView(context).apply {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.TIRAMISU) {
                    settings.setAlgorithmicDarkeningAllowed(true)
                } else if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                    settings.forceDark = WebSettings.FORCE_DARK_ON
                }
                webViewClient = WebViewClient()
                loadUrl("about:blank")
                loadDataWithBaseURL(null, html, "text/html", "UTF-8", null)
            }
        }
    )
}
