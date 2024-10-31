package com.github.kiolk.devto.data.repositories.datasources.network.mappers

import com.github.kiolk.devto.data.repositories.datasources.network.models.ArticleApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.FeedApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.FlareTagApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.GetArticlesParamsApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.PublicReactionCategoryApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.SearchArticleApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.SingleArticleApi
import com.github.kiolk.devto.domain.models.Article
import com.github.kiolk.devto.domain.models.User
import com.github.kiolk.devto.presentation.models.FlareTag
import com.github.kiolk.devto.presentation.models.GetArticlesParams
import com.github.kiolk.devto.presentation.models.PublicReactionCategory
import kotlinx.datetime.Instant

fun ArticleApi.toArticle(): Article {
    return Article(
        id = id,
        slug = slug,
        title = title,
        description = description,
        publishedAt = publishedAt,
        commentsCount = commentsCount,
        publicReactionCount = publicReactionCount,
        positiveReactionCount = positiveReactionCount,
        coverImage = coverImage,
        readingTimeMinutes = readingTimeMinutes,
        tagList = tagList,
        user = user.toUser(),
        organization = organization?.toOrganization(),
        flareTag = flareTag?.toFlareTag(),
        bodyHtml = bodyHtml.orEmpty(),
        bodyMarkdown = bodyMarkdown.orEmpty(),
    )
}

fun FeedApi.toArticle(): Article {
    return Article(
        id = id,
        slug = "",
        title = title,
        description = "",
        publishedAt = publishedTimestamp,
        commentsCount = commentsCount,
        publicReactionCount = publicReactionCount,
        positiveReactionCount = publicReactionCount,
        coverImage = mainImage,
        readingTimeMinutes = readingTimeMinutes,
        tagList = tagList,
        user = user.toUser(),
        organization = organization?.toOrganization(),
        flareTag = flareTag?.toFlareTag(),
        reactions = publicReactionCategories.map { it.toPublicReactionCategory() },
        topComments = topComments.map { it.toComment() }
    )
}

fun SearchArticleApi.toArticle(): Article {
    return Article(
        id = this.id ?: 0,
        slug = "",
        title = title.orEmpty(),
        description = "",
        publishedAt = Instant.fromEpochSeconds(publishedAtInt ?: 0),
        commentsCount = commentsCount ?: 0,
        publicReactionCount = publicReactionsCount ?: 0,
        positiveReactionCount = publicReactionsCount ?: 0,
        coverImage = "",
        readingTimeMinutes = readingTime ?: 0,
        tagList = tagList.orEmpty(),
        user = user?.toUser() ?: User(id = 0),
        organization = organization?.toOrganization(),
        flareTag = flareTag?.toFlareTag(),
        reactions = publicReactionCategories?.map { it.toPublicReactionCategory() }.orEmpty(),
        topComments = emptyList()
    )
}

fun FlareTagApi.toFlareTag(): FlareTag {
    return FlareTag(
        name = name,
        bgColorHex = bgColorHex,
        textColorHex = textColorHex,
    )
}

fun PublicReactionCategoryApi.toPublicReactionCategory(): PublicReactionCategory {
    return PublicReactionCategory(
        name = name,
        slug = slug,
        icon = icon,
        position = position,
    )
}

fun GetArticlesParams.toGetArticlesParamsApi(): GetArticlesParamsApi {
    return GetArticlesParamsApi(
        page = page,
        perPage = perPage,
        tag = tag,
        tags = tags,
        tagsExclude = tagsExclude,
        username = username,
        state = state,
        top = top,
        collectionId = collectionId,
        sortingType = sortingType,
        type = type,
        tagName = tagName,
    )
}

fun SingleArticleApi.mapToArticle(): Article {
    return Article(
        id = id,
        slug = slug.orEmpty(),
        title = title.orEmpty(),
        description = description.orEmpty(),
        publishedAt = publishedAt ?: Instant.DISTANT_PAST,
        commentsCount = commentsCount ?: 0,
        publicReactionCount = publicReactionsCount ?: 0,
        positiveReactionCount = positiveReactionsCount ?: 0,
        coverImage = coverImage,
        readingTimeMinutes = readingTimeMinutes ?: 0,
        tagList = tagList?.split(",")?.map { it.trim() }.orEmpty(),
        user = user?.toUser() ?: User(id = 0),
        organization = organization?.toOrganization(),
        flareTag = flareTag?.toFlareTag(),
        bodyHtml = bodyHtml.orEmpty(),
        bodyMarkdown = bodyMarkdown.orEmpty(),
    )
}
