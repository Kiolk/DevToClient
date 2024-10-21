package com.github.kiolk.devto.presentation.screens.search.model

import androidx.compose.ui.graphics.Color
import com.github.kiolk.devto.domain.models.Tag

data class TagSearchUi(
    val tag: Tag,
    val backgroundColor: Color,
    val badgeColor: Color,
    val summary: String? = null
) : SearchableUi
