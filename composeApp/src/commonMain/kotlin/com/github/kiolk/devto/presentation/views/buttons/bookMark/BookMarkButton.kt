package com.github.kiolk.devto.presentation.views.buttons.bookMark

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.kiolk.devto.presentation.screens.home.models.ArticleUi
import devto.composeapp.generated.resources.Res
import devto.composeapp.generated.resources.bookmark
import devto.composeapp.generated.resources.bookmark_filled
import org.jetbrains.compose.resources.painterResource

@Composable
fun BookMarkButton(article: ArticleUi, onBookmarkClick: (ArticleUi) -> Unit = {}) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()
    val isFocused: Boolean by interactionSource.collectIsFocusedAsState()

    val backgroundColor = when {
        isPressed || isFocused -> Color.Red
        else -> if (article.isBookmarked) Color.LightGray else Color.Transparent
    }

    Box(modifier = Modifier.size(20.dp)
        .focusable()
        .clickable(
            interactionSource = interactionSource,
            indication = null
        ) {
            onBookmarkClick(article)
        }
        .background(color = backgroundColor, shape = RoundedCornerShape(corner = CornerSize(4.dp)))
    ) {
        Image(
            modifier = Modifier
                .padding(4.dp),
            painter = painterResource(if (article.isBookmarked) Res.drawable.bookmark_filled else Res.drawable.bookmark),
            contentDescription = null
        )
    }
}
