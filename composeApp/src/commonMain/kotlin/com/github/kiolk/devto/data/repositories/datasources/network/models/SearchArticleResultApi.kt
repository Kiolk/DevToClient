package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchArticleResultApi(
    @SerialName("result")
    val result: List<SearchArticleApi>
)
