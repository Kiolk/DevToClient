package com.github.kiolk.devto.presentation.views.reactions

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.kiolk.devto.presentation.screens.home.models.ArticleUi
import org.jetbrains.compose.resources.painterResource

@Composable
fun Reactions(
    articleUi: ArticleUi,
) {
    if (articleUi.reactionsUi.total == 0) return

    Row(verticalAlignment = Alignment.CenterVertically) {
        articleUi.reactionsUi.types.forEachIndexed { index, reactionType ->
            Image(
                modifier = Modifier.padding(4.dp),
                painter = painterResource(reactionType.resId),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(reactionType.count.toString())
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}
