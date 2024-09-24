package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PublicReactionCategoryApi(
    @SerialName("slug")
    val slug: String,
    @SerialName("name")
    val name: String,
    @SerialName("icon")
    val icon: String,
    @SerialName("position")
    val position: Int
)
