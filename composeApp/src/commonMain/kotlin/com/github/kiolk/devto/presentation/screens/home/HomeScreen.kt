package com.github.kiolk.devto.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.kiolk.devto.presentation.screens.article.ArticleScreen
import com.github.kiolk.devto.presentation.screens.tag.TagScreen
import com.github.kiolk.devto.presentation.screens.user.UserScreen
import com.github.kiolk.devto.presentation.views.InfinityProgress
import com.github.kiolk.devto.presentation.views.ProgressSize
import com.github.kiolk.devto.presentation.views.article.ArticleItem
import com.github.kiolk.devto.presentation.views.chip.SortingChip
import com.github.kiolk.devto.utils.localisation.StringProvider
import org.koin.mp.KoinPlatform.getKoin

class HomeScreen : Screen {

    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<HomeScreenModel>()
        val stringProvider = getKoin().get<StringProvider>()

        val articlesState by screenModel.articlesState.collectAsState()
        val isLoading by screenModel.isLoading.collectAsState()
        val sortingType by screenModel.sortingType.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        val listState = rememberLazyListState()

        LaunchedEffect(key1 = listState.layoutInfo.visibleItemsInfo.lastOrNull()) {
            if (listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == listState.layoutInfo.totalItemsCount - 1) {
                screenModel.loadMoreArticles()
            }
        }
        var isTopChipsVisible by remember { mutableStateOf(false) }

        Column(verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                SortingChip(
                    currentType = SortingTypeUi.Related,
                    passedType = sortingType,
                    onClick = {
                        screenModel.onSortClick(SortingTypeUi.Related)
                        isTopChipsVisible = false
                    },
                    stringProvider = stringProvider,
                )
                FilterChip(
                    selected = sortingType == SortingTypeUi.Latest,
                    onClick = {
                        screenModel.onSortClick(SortingTypeUi.Latest)
                        isTopChipsVisible = false
                    },
                    content = { Text("Latest") })
                FilterChip(selected = sortingType is SortingTypeUi.Top, onClick = {
                    if (isTopChipsVisible) {
                        return@FilterChip
                    }
                    screenModel.onSortClick(SortingTypeUi.Top.Week)
                    isTopChipsVisible = !isTopChipsVisible
                }, content = { Text("Top") })
            }

            AnimatedVisibility(
                visible = isTopChipsVisible,
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    FilterChip(selected = sortingType == SortingTypeUi.Top.Week, onClick = {
                        screenModel.onSortClick(SortingTypeUi.Top.Week)
                    }, content = { Text("Week") })
                    FilterChip(selected = sortingType == SortingTypeUi.Top.Month, onClick = {
                        screenModel.onSortClick(SortingTypeUi.Top.Month)
                    }, content = { Text("Month") })
                    FilterChip(selected = sortingType == SortingTypeUi.Top.Year, onClick = {
                        screenModel.onSortClick(SortingTypeUi.Top.Year)
                    }, content = { Text("Year") })
                    FilterChip(selected = sortingType == SortingTypeUi.Top.Infinity, onClick = {
                        screenModel.onSortClick(SortingTypeUi.Top.Infinity)
                    }, content = { Text("Infinity") })
                }
            }

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
                        ArticleItem(articlesState[articleIndex],
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
                            },
                            onTagClick = {
                                navigator.push(TagScreen(it.name))
                            },
                            onBookmarkClick = {
                                screenModel.onBookmarkClick(it)
                            },
                            onUserClick = {
                                navigator.push(UserScreen(it))
                            })
                    } else if (isLoading) {
                        InfinityProgress()
                    }
                }
            }
        }
    }
}
