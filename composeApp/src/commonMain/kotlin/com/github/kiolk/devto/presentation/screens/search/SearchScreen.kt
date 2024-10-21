package com.github.kiolk.devto.presentation.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.kiolk.devto.presentation.screens.article.ArticleScreen
import com.github.kiolk.devto.presentation.screens.home.models.ArticleUi
import com.github.kiolk.devto.presentation.screens.search.model.CommentSearchUi
import com.github.kiolk.devto.presentation.screens.search.model.OrganizationSearchUi
import com.github.kiolk.devto.presentation.screens.search.model.SearchSortTypeUi
import com.github.kiolk.devto.presentation.screens.search.model.SearchTypeUi
import com.github.kiolk.devto.presentation.screens.search.model.SearchableUi
import com.github.kiolk.devto.presentation.screens.search.model.TagSearchUi
import com.github.kiolk.devto.presentation.screens.search.model.UserSearchUi
import com.github.kiolk.devto.presentation.screens.search.view.CommentSearchCard
import com.github.kiolk.devto.presentation.screens.search.view.OrganizationSearchCard
import com.github.kiolk.devto.presentation.screens.search.view.TagSearchCard
import com.github.kiolk.devto.presentation.screens.search.view.UserSearchCard
import com.github.kiolk.devto.presentation.screens.tag.TagScreen
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
        val stringProvider = getKoin().get<StringProvider>()
        val searchState by screenModel.searchState.collectAsState()
        val searchText by screenModel.searchText.collectAsState()
        val isLoading by screenModel.isLoading.collectAsState()
        val sortingType by screenModel.sortingType.collectAsState()
        val searchType by screenModel.searchType.collectAsState()

        val listState = rememberLazyListState()

        LaunchedEffect(key1 = listState.layoutInfo.visibleItemsInfo.lastOrNull()) {
            if (listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index == listState.layoutInfo.totalItemsCount - 1) {
                screenModel.loadMore()
            }
        }

        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)) {
                TextField(
                    modifier = Modifier.padding(8.dp),
                    value = searchText,
                    onValueChange = { screenModel.onSearchTextChanged(it) },
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                    label = { Text("Enter search text") }
                )
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
                    GetSearchResult(searchState, articleIndex, stringProvider)
                }
            }
            if (isLoading) {
                // TODO check display progress when loading new items
                InfinityProgress()
            }
        }
    }
}

@Composable
fun GetSearchResult(
    searchState: List<SearchableUi>,
    articleIndex: Int,
    stringProvider: StringProvider
) {
    val navigator = LocalNavigator.currentOrThrow
    when (val item = searchState[articleIndex]) {
        is ArticleUi -> ArticleItem(
            item,
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
        )

        is CommentSearchUi -> CommentSearchCard(item)
        is OrganizationSearchUi -> OrganizationSearchCard(item)
        is TagSearchUi -> TagSearchCard(item, onTagChecked = { navigator.push(TagScreen(it.tag)) })
        is UserSearchUi -> UserSearchCard(item)
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
        SearchTypeUi.values().forEach { type ->
            SortingChip(
                currentType = type,
                passedType = searchType,
                onClick = onSearchTypeClick,
                stringProvider = stringProvider,
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SortBar(
    sortingType: SearchSortTypeUi,
    stringProvider: StringProvider,
    onSortClick: (sortingType: SearchSortTypeUi) -> Unit = {},
) {
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp),
    ) {
        SearchSortTypeUi.values().forEach { type ->
            SortingChip(
                currentType = type,
                passedType = sortingType,
                onClick = onSortClick,
                stringProvider = stringProvider,
            )
        }
    }
}
