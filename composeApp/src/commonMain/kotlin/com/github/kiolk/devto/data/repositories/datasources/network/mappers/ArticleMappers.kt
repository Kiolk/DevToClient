package com.github.kiolk.devto.data.repositories.datasources.network.mappers

import Organization
import com.github.kiolk.devto.data.repositories.datasources.network.models.ArticleApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.TopCommentApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.FeedApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.FeedOrganizationApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.FeedUserApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.FlareTagApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.GetArticlesParamsApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.OrganizationApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.PublicReactionCategoryApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.UserApi
import com.github.kiolk.devto.presentation.models.Article
import com.github.kiolk.devto.presentation.models.Comment
import com.github.kiolk.devto.presentation.models.FlareTag
import com.github.kiolk.devto.presentation.models.GetArticlesParams
import com.github.kiolk.devto.presentation.models.PublicReactionCategory
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

fun UserApi.toUser(): User {
    return User(
        name = name,
        username = username,
        twitterUsername = twitterUsername,
        githubUsername = githubUsername,
        websiteUrl = websiteUrl,
        profileImage = profileImage,
        profileImage90 = profileImage90,
    )
}

fun FeedUserApi.toUser(): User {
    return User(
        name = name,
        username = username,
        twitterUsername = null,
        githubUsername = null,
        websiteUrl = null,
        profileImage = profileImageUrl,
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

fun FeedOrganizationApi.toOrganization(): Organization {
    return Organization(
        name = name,
        username = username,
        slug = slug,
        profileImage = profileImageUrl,
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
    )
}

private fun TopCommentApi.toComment(): Comment {
    return Comment(
        commentId = this.commentId,
        userId = this.userId,
        publishedTimestamp = this.publishedTimestamp, // Format Instant as needed
        path = this.path,
        username = this.username,
        name = this.name,
        profileImage90 = this.profileImage90,
        text = this.safeProcessedHtml
    )
}
