package com.github.kiolk.devto.data.repositories.datasources.network.mappers

import com.github.kiolk.devto.data.repositories.datasources.network.models.ArticleApi
import com.github.kiolk.devto.presentation.models.Article

fun ArticleApi.toArticle(): Article {
    return Article(
        this.id.toString(), this.title, this.description
    )
}
