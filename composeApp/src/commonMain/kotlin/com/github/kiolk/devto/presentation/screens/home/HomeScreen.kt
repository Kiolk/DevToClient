package com.github.kiolk.devto.presentation.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.kiolk.devto.presentation.screens.article.ArticleScreen
import com.github.kiolk.devto.presentation.screens.search.model.SortingTypeUi
import com.github.kiolk.devto.presentation.views.InfinityProgress
import com.github.kiolk.devto.presentation.views.ProgressSize
import com.github.kiolk.devto.presentation.views.article.ArticleItem
import com.github.kiolk.devto.presentation.views.chip.SortingChip
import com.github.kiolk.devto.utils.localisation.StringProvider
import com.github.kiolk.devto.utils.localisation.StringsKeys
import org.koin.mp.KoinPlatform.getKoin

open class HomeScreen : Screen {

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

        Column(verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxSize()) {
            Header()
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

    @Composable
    open fun Header() {
    }
}

@Composable
fun FilterBar(
    sortingType: SortingTypeUi,
    onSortClick: (sortingType: SortingTypeUi) -> Unit = {},
    stringProvider: StringProvider,
) {
    var isTopChipsVisible by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            SortingChip(
                currentType = SortingTypeUi.Related,
                passedType = sortingType,
                onClick = {
                    onSortClick(SortingTypeUi.Related)
                    isTopChipsVisible = false
                },
                stringProvider = stringProvider,
            )
            SortingChip(
                currentType = SortingTypeUi.Latest,
                passedType = sortingType,
                onClick = {
                    onSortClick(SortingTypeUi.Latest)
                    isTopChipsVisible = false
                },
                stringProvider = stringProvider,
            )
            SortingChip(
                currentType = SortingTypeUi.Top.Week,
                passedType = sortingType,
                isSelected = sortingType is SortingTypeUi.Top,
                onClick = {
                    if (isTopChipsVisible) {
                        return@SortingChip
                    }
                    onSortClick(SortingTypeUi.Top.Week)
                    isTopChipsVisible = !isTopChipsVisible
                },
                stringProvider = stringProvider,
                text = stringProvider.getString(StringsKeys.TOP)
            )
        }

        AnimatedVisibility(
            visible = isTopChipsVisible,
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                SortingChip(
                    currentType = SortingTypeUi.Top.Week,
                    passedType = sortingType,
                    onClick = {
                        onSortClick(SortingTypeUi.Top.Week)
                    },
                    stringProvider = stringProvider,
                )
                SortingChip(
                    currentType = SortingTypeUi.Top.Month,
                    passedType = sortingType,
                    onClick = {
                        onSortClick(SortingTypeUi.Top.Month)
                    },
                    stringProvider = stringProvider,
                )
                SortingChip(
                    currentType = SortingTypeUi.Top.Year,
                    passedType = sortingType,
                    onClick = {
                        onSortClick(SortingTypeUi.Top.Year)
                    },
                    stringProvider = stringProvider,
                )
                SortingChip(
                    currentType = SortingTypeUi.Top.Infinity,
                    passedType = sortingType,
                    onClick = {
                        onSortClick(SortingTypeUi.Top.Infinity)
                    },
                    stringProvider = stringProvider,
                )
            }
        }
    }
}
