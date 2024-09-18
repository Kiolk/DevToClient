package com.github.kiolk.devto.presentation.views.tag

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsFocusedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.github.kiolk.devto.presentation.screens.home.models.TagUi
import com.github.kiolk.devto.utils.colors.DevToColors

const val BACKGROUND_ALPHA = 0.2f
const val BORDER_ALPHA = 0.5f

@Composable
fun Tag(tag: TagUi, onTagClick: (TagUi) -> Unit = {}) {
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed: Boolean by interactionSource.collectIsPressedAsState()
    val isFocused: Boolean by interactionSource.collectIsFocusedAsState()

    val backgroundColor = when {
        isPressed || isFocused -> if (tag.backgroundColor != null) tag.backgroundColor.copy(alpha = BACKGROUND_ALPHA) else DevToColors.lightGray
        else -> if (tag.isFlare && tag.backgroundColor != null) tag.backgroundColor.copy(alpha = BACKGROUND_ALPHA) else DevToColors.white
    }

    val borderColor = when {
        isPressed || isFocused -> if (tag.backgroundColor != null) tag.backgroundColor.copy(
            alpha = BORDER_ALPHA
        ) else DevToColors.gray

        else -> if (tag.isFlare) DevToColors.transparent else DevToColors.white
    }

    val hashTagColor = when {
        isPressed || isFocused -> tag.backgroundColor ?: Color.Black
        else -> tag.backgroundColor ?: DevToColors.lightGray
    }

    Box(modifier = Modifier.background(shape = RoundedCornerShape(4.dp), color = backgroundColor)
        .border(width = 1.dp, color = borderColor, shape = RoundedCornerShape(4.dp))
        .clickable(interactionSource = interactionSource, indication = null) {
            onTagClick(tag)
        }) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(4.dp)) {
            Text("#", color = hashTagColor)
            Text(tag.name, modifier = Modifier, style = MaterialTheme.typography.body2)
        }
    }
}
