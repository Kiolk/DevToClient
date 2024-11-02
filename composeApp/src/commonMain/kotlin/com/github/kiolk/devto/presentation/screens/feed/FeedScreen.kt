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
import com.github.kiolk.devto.domain.models.Article
import com.github.kiolk.devto.domain.models.Comment
import com.github.kiolk.devto.domain.models.Organization
import com.github.kiolk.devto.domain.models.Searchable
import com.github.kiolk.devto.domain.models.Tag
import com.github.kiolk.devto.domain.models.User
import com.github.kiolk.devto.presentation.screens.feed.view.FeedBody
import com.github.kiolk.devto.presentation.screens.feed.view.FeedBodyScreenModel
import com.github.kiolk.devto.presentation.screens.feed.view.FeedParameter
import com.github.kiolk.devto.presentation.screens.feed.view.Header
import com.github.kiolk.devto.presentation.screens.feed.view.HeaderScreenModel
import com.github.kiolk.devto.presentation.screens.webView.WebViewScreen
import org.koin.core.parameter.parametersOf

class FeedScreen(private val tag: Searchable) : Screen {

    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val headerModel = koinScreenModel<HeaderScreenModel>(parameters = { parametersOf(tag.toFeedParam()) })
        val bodyModel = koinScreenModel<FeedBodyScreenModel>(parameters = { parametersOf(tag.toFeedParam()) })

        Column(modifier = Modifier.fillMaxWidth()) {
            Header(headerModel)
            FeedBody(bodyModel)
        }
    }
}

private fun Searchable.toFeedParam(): FeedParameter? {
    return when (this) {
        is Article -> TODO()
        is Comment -> TODO()
        is Organization -> TODO()
        is Tag -> FeedParameter.Tag(this.name)
        is User -> FeedParameter.User(this.id)
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
