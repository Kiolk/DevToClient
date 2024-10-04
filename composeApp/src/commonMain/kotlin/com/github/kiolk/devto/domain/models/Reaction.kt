package com.github.kiolk.devto.domain.models

data class Reaction(
    val result: String,
    val category: String,
    val id: Int,
    val reactableId: Int,
    val reactableType: String
)
