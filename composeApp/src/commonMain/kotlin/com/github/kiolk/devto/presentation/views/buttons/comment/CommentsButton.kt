package com.github.kiolk.devto.presentation.views.buttons.comment

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.kiolk.devto.presentation.screens.home.models.ArticleUi
import com.github.kiolk.devto.utils.localisation.StringProvider
import com.github.kiolk.devto.utils.localisation.StringsKeys
import devto.composeapp.generated.resources.Res
import devto.composeapp.generated.resources.ic_comment
import org.jetbrains.compose.resources.painterResource

@Composable
fun CommentsButton(articleUi: ArticleUi, onCommentsClick: (ArticleUi) -> Unit = {}, stringProvider: StringProvider) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .focusable(true)
            .clickable {
                onCommentsClick(articleUi)
            }
            .padding(4.dp)) {
        Box(modifier = Modifier.size(20.dp).padding(4.dp)) {
            Image(painter = painterResource(Res.drawable.ic_comment), contentDescription = null)
        }
        Text(
            if (articleUi.numberOfComments != 0) stringProvider.getQualityString(
                StringsKeys.COMMENTS,
                articleUi.numberOfComments,
            ) else stringProvider.getString(StringsKeys.ADD_COMMENT), style = MaterialTheme.typography.caption
        )
    }
}
