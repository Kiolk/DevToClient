package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.serialization.Serializable

@Serializable
data class ArticleApi(val id: Int, val title: String, val description: String)
