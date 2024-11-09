package com.github.kiolk.devto.presentation.screens.feed

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.koinScreenModel
import com.github.kiolk.devto.presentation.screens.feed.view.FeedBody
import com.github.kiolk.devto.presentation.screens.feed.view.FeedBodyScreenModel
import com.github.kiolk.devto.presentation.screens.feed.view.FeedParameter
import com.github.kiolk.devto.presentation.screens.feed.view.Header
import com.github.kiolk.devto.presentation.screens.feed.view.HeaderScreenModel
import com.github.kiolk.devto.presentation.screens.webView.WebViewScreen
import org.koin.core.parameter.parametersOf

class FeedScreen(private val param: FeedParameter) : Screen {

    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val headerModel = koinScreenModel<HeaderScreenModel>(parameters = { parametersOf(param) })
        val bodyModel = koinScreenModel<FeedBodyScreenModel>(parameters = { parametersOf(param) })

        Column(modifier = Modifier.fillMaxWidth()) {
            Header(headerModel)
            FeedBody(bodyModel)
        }
    }
}

@Composable
fun StubWebScreen(path: String) {
    Column {
        Text(
            "Screen in development. Available only web version",
            textAlign = TextAlign.Center,
            modifier = Modifier.background(Color.Red).fillMaxWidth()
                .align(Alignment.CenterHorizontally)
        )
        WebViewScreen(path)
    }
}
