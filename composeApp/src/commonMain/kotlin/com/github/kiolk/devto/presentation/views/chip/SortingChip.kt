package com.github.kiolk.devto.presentation.views.chip

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FilterChip
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.text.font.FontWeight.Companion.W700
import androidx.compose.ui.unit.dp
import com.github.kiolk.devto.presentation.screens.home.SortingTypeUi
import com.github.kiolk.devto.utils.localisation.StringProvider

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SortingChip(
    currentType: SortingTypeUi,
    passedType: SortingTypeUi,
    isSelected: Boolean? = null,
    onClick: (type: SortingTypeUi) -> Unit,
    stringProvider: StringProvider,
    text: String? = null,
) {
    FilterChip(
        selected = isSelected ?: (passedType == currentType),
        shape = RoundedCornerShape(size = 4.dp),
        colors = ChipDefaults.filterChipColors(
            backgroundColor = MaterialTheme.colors.secondary.copy(
                alpha = 0.5f
            ), selectedBackgroundColor = MaterialTheme.colors.primary.copy(alpha = 0.5f)
        ),
        onClick = {
            onClick(currentType)
        },
        content = {
            Text(
                text ?: stringProvider.getString(currentType.key),
                modifier = Modifier.padding(8.dp),
                style = MaterialTheme.typography.caption.copy(
                    fontWeight = if (isSelected ?: (passedType == currentType)) W700 else W400
                )
            )
        })
}
