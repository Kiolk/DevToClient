package com.github.kiolk.devto.presentation.views.buttons.bookMark

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.unit.dp
import com.github.kiolk.devto.presentation.screens.home.models.ArticleUi
import devto.composeapp.generated.resources.Res
import io.github.alexzhirkevich.compottie.LottieClipSpec
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import org.jetbrains.compose.resources.ExperimentalResourceApi

/**
 * TODO This element required some improvements. Supporting different themes, play sound on press,
 *  optimisation.
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
fun BookMarkButton(article: ArticleUi, onBookmarkClick: (ArticleUi) -> Unit = {}) {
    var isPlaying by remember(article.article.id) {
        mutableStateOf(false)
    }

    val composition by rememberLottieComposition(article.article.id) {
        LottieCompositionSpec.JsonString(
            Res.readBytes("files/bookmarks.json").decodeToString()
        )
    }

    val compositionStatic by rememberLottieComposition(article.article.id) {
        LottieCompositionSpec.JsonString(
            Res.readBytes("files/bookmarks.json").decodeToString()
        )
    }

    val progress by animateLottieCompositionAsState(
        composition,
        isPlaying = isPlaying,
        clipSpec = LottieClipSpec.Progress(0.05f, 0.5f),
    )

    val progressIsBookmarked by animateLottieCompositionAsState(
        compositionStatic,
        isPlaying = true,
        clipSpec = LottieClipSpec.Progress(0.4f, 0.5f),
    )

    val progressUnBookmarked by animateLottieCompositionAsState(
        compositionStatic,
        isPlaying = false,
        clipSpec = LottieClipSpec.Progress(0.01f, 0.01f),
    )

    val haptic = LocalHapticFeedback.current

    Box(modifier = Modifier
        .size(20.dp)
        .focusable()
        .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) {
            isPlaying = !isPlaying && !article.isBookmarked
            onBookmarkClick(article)
            haptic.performHapticFeedback(HapticFeedbackType.LongPress)
        }) {
        Image(
            painter =
            if (isPlaying) {
                rememberLottiePainter(
                    composition = composition,
                    progress = { progress },
                )
            } else {
                rememberLottiePainter(
                    composition = compositionStatic,
                    progress = {
                        if (article.isBookmarked) progressIsBookmarked else progressUnBookmarked
                    },
                )
            },
            contentDescription = "Lottie animation"
        )
    }
}
