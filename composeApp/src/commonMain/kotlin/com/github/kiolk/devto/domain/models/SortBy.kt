package com.github.kiolk.devto.domain.models

enum class SortBy(val value: String) {
    HOTNESS_SCORE("hotness_score"), PUBLISHED_AT("published_at"), PUBLIC_REACTION_COUNT("public_reactions_count")
}
