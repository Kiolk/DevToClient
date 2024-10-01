package com.github.kiolk.devto.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import devto.composeapp.generated.resources.Res
import io.github.alexzhirkevich.compottie.Compottie
import io.github.alexzhirkevich.compottie.LottieCompositionSpec
import io.github.alexzhirkevich.compottie.animateLottieCompositionAsState
import io.github.alexzhirkevich.compottie.rememberLottieComposition
import io.github.alexzhirkevich.compottie.rememberLottiePainter
import org.jetbrains.compose.resources.ExperimentalResourceApi

// TODO implement dynamic colors for support dark theme https://github.com/Kiolk/DevToClient/issues/4
@OptIn(ExperimentalResourceApi::class)
@Composable
fun InfinityProgress(size: ProgressSize = ProgressSize.Small) {

    val composition by rememberLottieComposition {
        LottieCompositionSpec.JsonString(
            Res.readBytes("files/progress.json").decodeToString()
        )
    }

    val progress by animateLottieCompositionAsState(
        composition,
        isPlaying = true,
        iterations = Compottie.IterateForever
    )
    Box(
        modifier = Modifier
            .size(size.size)
    ) {
        Image(
            painter =
            rememberLottiePainter(
                composition = composition,
                progress = { progress },
            ),
            contentDescription = "Progress Lottie animation"
        )
    }
}

sealed class ProgressSize(val size: Dp) {
    data object Small : ProgressSize(40.dp)
    data object Middle : ProgressSize(60.dp)
    data object Large : ProgressSize(80.dp)
}
