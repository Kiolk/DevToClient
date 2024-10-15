package com.github.kiolk.devto.presentation.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.kiolk.devto.domain.models.Article
import com.github.kiolk.devto.presentation.screens.article.ArticleScreen
import com.github.kiolk.devto.presentation.screens.home.mappers.mapToArticleUi
import com.github.kiolk.devto.presentation.screens.search.model.SearchSortTypeUi
import com.github.kiolk.devto.presentation.screens.search.model.SearchTypeUi
import com.github.kiolk.devto.presentation.screens.tag.TagScreen
import com.github.kiolk.devto.presentation.screens.user.UserScreen
import com.github.kiolk.devto.presentation.views.InfinityProgress
import com.github.kiolk.devto.presentation.views.ProgressSize
import com.github.kiolk.devto.presentation.views.article.ArticleItem
import com.github.kiolk.devto.presentation.views.chip.SortingChip
import com.github.kiolk.devto.utils.localisation.StringProvider
import org.koin.mp.KoinPlatform.getKoin

class SearchScreen : Screen {

    @Composable
    override fun Content() {
        val screenModel: SearchScreenModel = koinScreenModel<SearchScreenModel>()
        screenModel.pagination
        val stringProvider = getKoin().get<StringProvider>()
        val searchState by screenModel.searchState.collectAsState()
        val isLoading by screenModel.isLoading.collectAsState()
        val sortingType by screenModel.sortingType.collectAsState()
        val searchType by screenModel.searchType.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        val listState = rememberLazyListState()

        LaunchedEffect(key1 = listState.layoutInfo.visibleItemsInfo.lastOrNull()) {
            if (listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == listState.layoutInfo.totalItemsCount - 1) {
                screenModel.loadMore()
            }
        }

        Column(verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)) {
                SortBar(sortingType, stringProvider) {
                    screenModel.onSortClick(it)
                }
                SearchByTypeBar(searchType, stringProvider) {
                    screenModel.onSearchByTypeClicked(it)
                }
            }
            if (searchState.isEmpty()) {
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
                items(searchState.size) { articleIndex ->
                    if (articleIndex < searchState.size - 1) {
                        ArticleItem(
                            (searchState[articleIndex] as Article).mapToArticleUi(
                                stringProvider
                            ),
                            stringProvider = stringProvider,
                            onArticleClick = { article, commentId, showComments ->
                                navigator.push(
                                    ArticleScreen(
                                        article.article.user.username,
                                        article.article.slug,
                                        article.article.id.toString(),
                                        commentId,
                                        showComments,
                                    )
                                )
                            },
                            onTagClick = {
                                navigator.push(TagScreen(it.name))
                            },
                            onBookmarkClick = {
                            },
                            onUserClick = {
                                navigator.push(UserScreen(it))
                            }
                        )
                    } else if (isLoading) {
                        InfinityProgress()
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SearchByTypeBar(
    searchType: SearchTypeUi,
    stringProvider: StringProvider,
    onSearchTypeClick: (searchType: SearchTypeUi) -> Unit = {},
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        SortingChip(
            currentType = SearchTypeUi.Post,
            passedType = searchType,
            onClick = onSearchTypeClick,
            stringProvider = stringProvider,
        )
        SortingChip(
            currentType = SearchTypeUi.User,
            passedType = searchType,
            onClick = onSearchTypeClick,
            stringProvider = stringProvider,
        )
        SortingChip(
            currentType = SearchTypeUi.Organization,
            passedType = searchType,
            onClick = onSearchTypeClick,
            stringProvider = stringProvider,
        )
        SortingChip(
            currentType = SearchTypeUi.Comment,
            passedType = searchType,
            onClick = onSearchTypeClick,
            stringProvider = stringProvider,
        )
        SortingChip(
            currentType = SearchTypeUi.Tag,
            passedType = searchType,
            onClick = onSearchTypeClick,
            stringProvider = stringProvider,
        )
    }
}

@Composable
fun SortBar(
    sortingType: SearchSortTypeUi,
    stringProvider: StringProvider,
    onSortClick: (sortingType: SearchSortTypeUi) -> Unit = {},
) {
    Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
        ) {
            SortingChip(
                currentType = SearchSortTypeUi.MostRelevant,
                passedType = sortingType,
                onClick = {
                    onSortClick(SearchSortTypeUi.MostRelevant)
                },
                stringProvider = stringProvider,
            )
            SortingChip(
                currentType = SearchSortTypeUi.Newest,
                passedType = sortingType,
                onClick = {
                    onSortClick(SearchSortTypeUi.Newest)
                },
                stringProvider = stringProvider,
            )
            SortingChip(
                currentType = SearchSortTypeUi.Oldest,
                passedType = sortingType,
                onClick = {
                    onSortClick(SearchSortTypeUi.Oldest)
                },
                stringProvider = stringProvider,
            )
        }
    }
}
