package com.github.kiolk.devto.presentation.screens.home.models

import androidx.compose.ui.graphics.Color

data class TagUi(
    val name: String,
    val backgroundColor: Color? = null,
    val textColor: Color? = null,
    val isFlare: Boolean = false,
)
