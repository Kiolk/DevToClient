package com.github.kiolk.devto.domain.models

data class Tag(
    val id: Int = 0,
    val name: String = "",
    val hotnessScore: Int = 0,
    val supported: Boolean = false,
    val shortSummary: String = "",
    val rulesHtml: String = "",
    val bgColorHex: String = "#FFFFFF",
    val badgeImageUrl: String = "",
) : Searchable
