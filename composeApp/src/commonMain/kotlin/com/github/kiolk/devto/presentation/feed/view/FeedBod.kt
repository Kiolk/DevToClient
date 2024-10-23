package com.github.kiolk.devto.presentation.feed.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.kiolk.devto.presentation.screens.article.ArticleScreen
import com.github.kiolk.devto.presentation.screens.home.FilterBar
import com.github.kiolk.devto.presentation.views.InfinityProgress
import com.github.kiolk.devto.presentation.views.ProgressSize
import com.github.kiolk.devto.presentation.views.article.ArticleItem
import com.github.kiolk.devto.utils.localisation.StringProvider
import org.koin.mp.KoinPlatform.getKoin

@Composable
fun FeedBody(screenModel: FeedBodyScreenModel) {
    val stringProvider = getKoin().get<StringProvider>()

    val articlesState by screenModel.feedState.collectAsState()
    val isLoading by screenModel.isLoading.collectAsState()
    val sortingType by screenModel.sortingType.collectAsState()
    val navigator = LocalNavigator.currentOrThrow

    val listState = rememberLazyListState()

    LaunchedEffect(key1 = listState.layoutInfo.visibleItemsInfo.lastOrNull()) {
        if (listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == listState.layoutInfo.totalItemsCount - 1) {
            screenModel.loadMoreArticles()
        }
    }

    Column(verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxSize()) {
        FilterBar(sortingType, { screenModel.onSortClick(it) }, stringProvider)

        if (articlesState.isEmpty()) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                InfinityProgress(size = ProgressSize.Large)
            }
            return
        }

        LazyColumn(
            state = listState,
            modifier = Modifier,
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(articlesState.size) { articleIndex ->
                if (articleIndex < articlesState.size - 1) {
                    ArticleItem(
                        articlesState[articleIndex],
                        stringProvider = stringProvider,
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
                        }
                    )
                } else if (isLoading) {
                    InfinityProgress()
                }
            }
        }
    }
}
