package com.github.kiolk.devto.presentation.screens.home.mappers

import com.github.kiolk.devto.presentation.models.Article
import com.github.kiolk.devto.presentation.screens.home.models.ArticleUi
import com.github.kiolk.devto.utils.localisation.StringProvider
import com.github.kiolk.devto.utils.toPublicationDate
import com.github.kiolk.devto.utils.toPublicationDateAgo

fun Article.mapToArticleUi(stringProvider: StringProvider): ArticleUi {
    return ArticleUi(
        article = this,
        userName = this.user.name,
        organisationName = this.organization?.name,
        publishedAt = this.publishedAt.toPublicationDate(),
        publishedAgo = this.publishedAt.toPublicationDateAgo(stringProvider),
        title = this.title,
        description = this.description,
        tags = this.tagList.map { it.toTagUi(this.flareTag) }
    )
}
