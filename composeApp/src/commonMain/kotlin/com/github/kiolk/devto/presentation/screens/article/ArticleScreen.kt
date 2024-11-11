@file:Suppress("MagicNumber")

package com.github.kiolk.devto.presentation.screens.article

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.navigator.currentOrThrow
import coil3.compose.AsyncImage
import com.github.kiolk.devto.presentation.screens.comments.CommentsScreen
import com.github.kiolk.devto.presentation.screens.feed.FeedScreen
import com.github.kiolk.devto.presentation.screens.feed.view.FeedParameter
import com.github.kiolk.devto.presentation.screens.home.models.ArticleUi
import com.github.kiolk.devto.presentation.screens.home.models.TagUi
import com.github.kiolk.devto.presentation.screens.user.UserScreen
import com.github.kiolk.devto.presentation.screens.webView.WebContent
import com.github.kiolk.devto.presentation.views.article.ArticleTags
import com.github.kiolk.devto.presentation.views.article.PublicationDate
import com.github.kiolk.devto.presentation.views.article.UserNameWithOrganisation
import com.github.kiolk.devto.presentation.views.avatar.UserOrganisationAvatar
import com.github.kiolk.devto.presentation.views.comments.CommentsFeed
import com.github.kiolk.devto.presentation.views.reactions.Reactions
import com.github.kiolk.devto.utils.localisation.StringProvider
import devto.composeapp.generated.resources.Res
import devto.composeapp.generated.resources.ic_back
import org.jetbrains.compose.resources.painterResource
import org.koin.core.parameter.parametersOf
import org.koin.mp.KoinPlatform.getKoin

private val headerHeight = 250.dp
private val toolbarHeight = 56.dp

private val paddingMedium = 16.dp

private val titlePaddingStart = 16.dp
private val titlePaddingEnd = 72.dp

private const val SCALE_START = 1f
private const val SCALE_END = 0.66f

private val Blue500 = Color(0xff026586)
private val Blue800 = Color(0xff032C45)
private val Black900 = Color(0x88000000)

class ArticleScreen(private val openArticlesParams: OpenArticleParams) : Screen {

    override val key: ScreenKey = uniqueScreenKey
    val stringProvider = getKoin().get<StringProvider>()

    @Composable
    override fun Content() {
        val screenModel =
            koinScreenModel<ArticleScreenModel>(parameters = { parametersOf(openArticlesParams) })

        val article by screenModel.articleUi.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        Surface {
            CollapsingToolbarParallaxEffect(
                article,
                Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colors.surface),
                stringProvider = stringProvider,
                onTagClicked = {
                    navigator.push(FeedScreen(FeedParameter.Tag(it.name)))
                },
                navigator = navigator,
            )
        }
    }
}

@Composable
fun CollapsingToolbarParallaxEffect(
    article: ArticleUi?,
    modifier: Modifier = Modifier,
    stringProvider: StringProvider,
    onTagClicked: (tagUi: TagUi) -> Unit = {},
    navigator: Navigator,
) {
    val scroll: ScrollState = rememberScrollState(0)

    val headerHeightPx = with(LocalDensity.current) { headerHeight.toPx() }
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.toPx() }

    Box(modifier = modifier) {
        Header(
            imageUrl = article?.article?.coverImage,
            scroll = scroll,
            headerHeightPx = headerHeightPx,
            modifier = Modifier
                .fillMaxWidth()
                .height(headerHeight)
        )
        article?.let {
            Body(
                article,
                onTagClicked,
                stringProvider = stringProvider,
                scroll = scroll,
                modifier = Modifier.fillMaxSize()
            )
        }
        Toolbar(
            scroll = scroll,
            headerHeightPx = headerHeightPx,
            toolbarHeightPx = toolbarHeightPx,
            navigator = navigator,
        )
        Title(title = article?.title.orEmpty(), scroll = scroll)
        Title(title = article?.title.orEmpty(), scroll = scroll)
    }
}

@Composable
private fun Header(
    imageUrl: String?,
    scroll: ScrollState,
    headerHeightPx: Float,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .graphicsLayer {
                translationY = -scroll.value.toFloat() / 2f // Parallax effect
                alpha = (-1f / headerHeightPx) * scroll.value + 1
            }
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Box(
            Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Black900),
                        startY = 3 * headerHeightPx / 4 // Gradient applied to wrap the title only
                    )
                )
        )
    }
}

@Composable
private fun Body(
    articleUi: ArticleUi,
    onTagClicked: (tagUi: TagUi) -> Unit = {},
    scroll: ScrollState,
    modifier: Modifier = Modifier,
    stringProvider: StringProvider,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.verticalScroll(scroll)
    ) {
        val navigator = LocalNavigator.currentOrThrow

        Spacer(Modifier.height(headerHeight))
        Column {
            Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.Top) {
                UserOrganisationAvatar(
                    articleUi.article.user,
                    articleUi.article.organization,
                    onUserClick = { navigator.push(FeedScreen(FeedParameter.User(articleUi.article.user.id))) }
                )
                Column(
                    modifier = Modifier.padding(start = 2.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Row {
                        Column(verticalArrangement = Arrangement.Top) {
                            UserNameWithOrganisation(
                                articleUi.article.user,
                                articleUi.article.organization,
                                onUserClick = { navigator.push(UserScreen(it)) },
                            )
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                PublicationDate(articleUi)
                                Spacer(Modifier.weight(1f))
                            }
                        }
                    }
                }
            }
            ArticleTags(articleUi.tags, articleUi.article.flareTag, {
                onTagClicked(it)
            })
            Reactions(articleUi)
            WebContent(
                articleUi.article.bodyMarkdown,
                maxLines = 1000,
            ) { }
            CommentsFeed(
                articleUi.comments,
                articleUi.totalComments,
                stringProvider = stringProvider,
                onUserClick = { navigator.push(FeedScreen(FeedParameter.User(it))) },
                onCommentClick = { navigator.push(CommentsScreen(it)) }
            )
        }
    }
}

@Composable
private fun Toolbar(
    scroll: ScrollState,
    headerHeightPx: Float,
    toolbarHeightPx: Float,
    modifier: Modifier = Modifier,
    navigator: Navigator,
) {
    val toolbarBottom by remember {
        mutableStateOf(headerHeightPx - toolbarHeightPx)
    }

    val showToolbar by remember {
        derivedStateOf {
            scroll.value >= toolbarBottom
        }
    }

    AnimatedVisibility(
        modifier = modifier,
        visible = showToolbar,
        enter = fadeIn(animationSpec = tween(300)),
        exit = fadeOut(animationSpec = tween(300))
    ) {
        TopAppBar(
            modifier = Modifier.background(
                brush = Brush.horizontalGradient(
                    listOf(Blue500, Blue800)
                )
            ),
            navigationIcon = {
                IconButton(
                    onClick = {
                        navigator.pop()
                    },
                    modifier = Modifier
                        .padding(16.dp)
                        .size(24.dp)
                ) {
                    Icon(
                        painter = painterResource(Res.drawable.ic_back),
                        contentDescription = null,
                        tint = MaterialTheme.colors.onSurface
                    )
                }
            },
            title = {},
            backgroundColor = Color.Transparent,
            elevation = 0.dp
        )
    }
}

@Composable
private fun Title(
    title: String,
    scroll: ScrollState,
    modifier: Modifier = Modifier
) {
    var titleHeightPx by remember { mutableStateOf(0f) }
    var titleWidthPx by remember { mutableStateOf(0f) }

    Text(
        text = title,
        fontSize = 30.sp,
        fontWeight = FontWeight.Bold,
        color = Color.White,
        modifier = modifier
            .graphicsLayer {
                val collapseRange: Float = (headerHeight.toPx() - toolbarHeight.toPx())
                val collapseFraction: Float = (scroll.value / collapseRange).coerceIn(0f, 1f)

                val scaleXY = lerp(
                    SCALE_START.dp,
                    SCALE_END.dp,
                    collapseFraction
                )

                val titleExtraStartPadding = titleWidthPx.toDp() * (1 - scaleXY.value) / 2f

                val titleYFirstInterpolatedPoint = lerp(
                    headerHeight - titleHeightPx.toDp() - paddingMedium,
                    headerHeight / 2,
                    collapseFraction
                )

                val titleXFirstInterpolatedPoint = lerp(
                    titlePaddingStart,
                    (titlePaddingEnd - titleExtraStartPadding) * 5 / 4,
                    collapseFraction
                )

                val titleYSecondInterpolatedPoint = lerp(
                    headerHeight / 2,
                    toolbarHeight / 2 - titleHeightPx.toDp() / 2,
                    collapseFraction
                )

                val titleXSecondInterpolatedPoint = lerp(
                    (titlePaddingEnd - titleExtraStartPadding) * 5 / 4,
                    titlePaddingEnd - titleExtraStartPadding,
                    collapseFraction
                )

                val titleY = lerp(
                    titleYFirstInterpolatedPoint,
                    titleYSecondInterpolatedPoint,
                    collapseFraction
                )

                val titleX = lerp(
                    titleXFirstInterpolatedPoint,
                    titleXSecondInterpolatedPoint,
                    collapseFraction
                )

                translationY = titleY.toPx()
                translationX = titleX.toPx()
                scaleX = scaleXY.value
                scaleY = scaleXY.value
            }
            .onGloballyPositioned {
                titleHeightPx = it.size.height.toFloat()
                titleWidthPx = it.size.width.toFloat()
            }
    )
}
