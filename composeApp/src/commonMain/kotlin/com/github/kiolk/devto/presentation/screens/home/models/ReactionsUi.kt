package com.github.kiolk.devto.presentation.screens.home.models

data class ReactionsUi(
    val types: List<ReactionType> = emptyList(),
    val total: Int = 0,
)
