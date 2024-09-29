import Foundation
import SwiftUI
import WebKit

struct WebView: UIViewRepresentable {
    let url: String

    func makeUIView(context: Context) -> WKWebView {
        let webView = WKWebView()
        loadRequest(webView: webView)
        return webView
    }

    func updateUIView(_ uiView: WKWebView, context: Context) {
        loadRequest(webView: uiView)
    }

    private func loadRequest(webView: WKWebView) {
        if let url = URL(string: url) {
            let request = URLRequest(url: url)
            webView.load(request)
        }
    }
}
