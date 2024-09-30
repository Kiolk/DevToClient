package com.github.kiolk.devto.presentation.screens.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
        val isLoading by screenModel.isLoading.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        val listState = rememberLazyListState()

        LaunchedEffect(key1 = listState.layoutInfo.visibleItemsInfo.lastOrNull()) {
            if (listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == listState.layoutInfo.totalItemsCount - 1) {
                screenModel.loadMoreArticles()
            }
        }

        LazyColumn(state = listState) {
            items(articlesState.size) { articleIndex ->
                if (articleIndex < articlesState.size - 1) {
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
                } else if (isLoading) {
                    CircularProgressIndicator()
                }
            }
        }
    }
}
