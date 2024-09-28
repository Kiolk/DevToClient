package com.github.kiolk.devto.presentation.screens.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.kiolk.devto.presentation.screens.article.ArticleScreen
import com.github.kiolk.devto.presentation.screens.tag.TagScreen
import com.github.kiolk.devto.presentation.screens.user.UserScreen
import com.github.kiolk.devto.presentation.views.article.ArticleItem
import com.github.kiolk.devto.utils.localisation.StringProvider
import org.koin.mp.KoinPlatform.getKoin

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<HomeScreenModel>()
        val stringProvider = getKoin().get<StringProvider>()

        val articlesState by screenModel.articlesState.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        LazyColumn {
            items(articlesState.size) { articleIndex ->
                ArticleItem(articlesState[articleIndex], stringProvider = stringProvider,
                    onArticleClick = { article, commentId, showComments ->
                        navigator.push(
                            ArticleScreen(
                                article.article.user.username,
                                article.article.slug,
                                article.article.id.toString(),
                                commentId,
                                showComments
                            )
                        )
                    }, onTagClick = {
                        navigator.push(TagScreen(it.name))
                    }, onBookmarkClick = {
                        screenModel.onBookmarkClick(it)
                    }, onUserClick = {
                        navigator.push(UserScreen(it))
                    })
            }
        }
    }
}
