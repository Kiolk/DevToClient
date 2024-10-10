package com.github.kiolk.devto.presentation.screens.webView

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import androidx.compose.ui.unit.dp
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRect
import platform.Foundation.NSURL
import platform.Foundation.NSURLRequest
import platform.QuartzCore.CATransaction
import platform.UIKit.UIView
import platform.WebKit.WKNavigationAction
import platform.WebKit.WKNavigationActionPolicy
import platform.WebKit.WKNavigationDelegateProtocol
import platform.WebKit.WKNavigationTypeLinkActivated
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration
import platform.darwin.NSObject

/**
 * TODO Need correct implement displaying content in WebView https://github.com/Kiolk/DevToClient/issues/3
 */
@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun WebViewScreen(url: String) {
    val content = remember { url }
    val webView = remember { WKWebView() }
    val onUrlClicked: (String) -> Unit = {}
    val navigationDelegate = rememberWebViewDelegate(onUrlClicked)

    webView.navigationDelegate = navigationDelegate
    UIKitView(
        modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
        factory = {
            val container = UIView()
            webView.apply {
                WKWebViewConfiguration().apply {
                    allowsInlineMediaPlayback = true
                    allowsAirPlayForMediaPlayback = true
                    allowsPictureInPictureMediaPlayback = true
                }
            }
            container.addSubview(webView)
            container
        },
        onResize = { view: UIView, rect: CValue<CGRect> ->
            CATransaction.begin()
            CATransaction.setValue(true, "kCATransactionDisableActions")
            view.layer.setFrame(rect)
            webView.setFrame(rect)
            CATransaction.commit()
        }
    )

    webView.loadRequest(NSURLRequest(uRL = NSURL(string = content)))
}

@Composable
private fun rememberWebViewDelegate(onUrlClicked: (String) -> Unit): WKNavigationDelegateProtocol {
    return object : NSObject(), WKNavigationDelegateProtocol {
        override fun webView(
            webView: WKWebView,
            decidePolicyForNavigationAction: WKNavigationAction,
            decisionHandler: (WKNavigationActionPolicy) -> Unit
        ) {
            val navigationType = decidePolicyForNavigationAction.navigationType
            val request = decidePolicyForNavigationAction.request

            when (navigationType) {
                WKNavigationTypeLinkActivated -> {
                    if (decidePolicyForNavigationAction.targetFrame == null) {
                        webView.loadRequest(request)
                    }
                    onUrlClicked(request.URL?.absoluteString.orEmpty())
                    println(request.URL?.absoluteString.orEmpty())
                    decisionHandler(WKNavigationActionPolicy.WKNavigationActionPolicyAllow)
                }
                else -> decisionHandler(WKNavigationActionPolicy.WKNavigationActionPolicyAllow)
            }
        }
    }
}

@Composable
actual fun WebContent(html: String, function: () -> Unit) {
//TODO Need implement logic for this issue https://github.com/Kiolk/DevToClient/issues/2
    Text(html)
}

