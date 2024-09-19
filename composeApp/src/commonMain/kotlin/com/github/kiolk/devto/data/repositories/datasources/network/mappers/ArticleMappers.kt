package com.github.kiolk.devto.data.repositories.datasources.network.mappers

import Organization
import com.github.kiolk.devto.data.repositories.datasources.network.models.ArticleApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.FlareTagApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.GetArticlesParamsApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.OrganizationApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.UserApi
import com.github.kiolk.devto.presentation.models.Article
import com.github.kiolk.devto.presentation.models.FlareTag
import com.github.kiolk.devto.presentation.models.GetArticlesParams
import com.github.kiolk.devto.presentation.models.User

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
    )
}

fun UserApi.toUser(): User {
    return User(
        name = name,
        username = username,
        twitterUsername = twitterUsername,
        githubUsername = githubUsername,
        userId = userId,
        websiteUrl = websiteUrl,
        profileImage = profileImage,
        profileImage90 = profileImage90,
    )
}

fun OrganizationApi.toOrganization(): Organization {
    return Organization(
        name = name,
        username = username,
        slug = slug,
        profileImage = profileImage,
        profileImage90 = profileImage90,
    )
}

fun FlareTagApi.toFlareTag(): FlareTag {
    return FlareTag(
        name = name,
        bgColorHex = bgColorHex,
        textColorHex = textColorHex,
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
    )
}
