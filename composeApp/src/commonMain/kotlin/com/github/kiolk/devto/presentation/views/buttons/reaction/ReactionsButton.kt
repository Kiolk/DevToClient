package com.github.kiolk.devto.presentation.views.buttons.reaction

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.kiolk.devto.presentation.screens.home.models.ArticleUi
import com.github.kiolk.devto.utils.localisation.StringProvider
import com.github.kiolk.devto.utils.localisation.StringsKeys
import org.jetbrains.compose.resources.painterResource

@Composable
fun ReactionsButton(
    articleUi: ArticleUi,
    stringProvider: StringProvider,
    onReactionClick: (ArticleUi) -> Unit = {},
) {
    if (articleUi.reactionsUi.total == 0) return

    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .focusable(true)
            .clickable {
                onReactionClick(articleUi)
            }
            .padding(4.dp)) {

        Box {
            articleUi.reactionsUi.types.forEachIndexed { index, reactionType ->
                Row(modifier = Modifier.align(Alignment.BottomEnd)) {
                    Box(
                        modifier = Modifier.size(20.dp).background(color = Color.LightGray, shape = CircleShape)
                            .border(border = BorderStroke(1.dp, color = MaterialTheme.colors.background), shape = CircleShape)
                    ) {
                        Image(modifier = Modifier.padding(4.dp), painter = painterResource(reactionType.resId), contentDescription = null)
                    }
                    Spacer(modifier = Modifier.width((index * 10).dp))
                }
            }
        }

        Text(
            modifier = Modifier.padding(4.dp),
            text = stringProvider.getQualityString(StringsKeys.REACTIONS, articleUi.reactionsUi.total),
            style = MaterialTheme.typography.caption
        )
    }
}
