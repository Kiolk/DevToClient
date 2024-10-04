package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReactionApi(
    @SerialName("result") val result: String,
    @SerialName("category") val category: String,
    @SerialName("id") val id: Int,
    @SerialName("reactable_id") val reactableId: Int,
    @SerialName("reactable_type") val reactableType: String
)
