package com.github.kiolk.devto.presentation.screens.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.github.kiolk.devto.presentation.screens.webView.WebViewScreen
import org.koin.core.parameter.parametersOf

class TagScreen(private val tagName: String) : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<TagScreenModel>(parameters = { parametersOf(tagName) })

        val tagName = screenModel.tagName.collectAsState().value
        StubWebScreen("https://dev.to/t/$tagName")
    }
}

@Composable
fun StubWebScreen(path: String) {
    Column {
        Text(
            "Screen in development. Available only web version",
            textAlign = TextAlign.Center,
            modifier = Modifier.background(Color.Red).fillMaxWidth().align(Alignment.CenterHorizontally)
        )
        WebViewScreen(path)
    }
}
