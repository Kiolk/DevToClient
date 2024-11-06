package com.github.kiolk.devto.presentation.screens.comments

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.kiolk.devto.presentation.screens.feed.FeedScreen
import com.github.kiolk.devto.presentation.screens.feed.view.FeedParameter
import com.github.kiolk.devto.presentation.views.comments.CommentsFeed
import org.koin.core.parameter.parametersOf

class CommentsScreen(private val commentId: String) : Screen {

    override val key: ScreenKey = uniqueScreenKey

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<CommentsScreenModel> { parametersOf(commentId) }

        val comments by screenModel.commentsUi.collectAsState()
        val totalComments by screenModel.totalComments.collectAsState()
        val navigator = LocalNavigator.currentOrThrow

        CommentsFeed(
            comments,
            totalComments,
            onUserClick = { navigator.push(FeedScreen(FeedParameter.User(it))) },
            onCommentClick = { navigator.push(CommentsScreen(it)) }
        )
    }
}
