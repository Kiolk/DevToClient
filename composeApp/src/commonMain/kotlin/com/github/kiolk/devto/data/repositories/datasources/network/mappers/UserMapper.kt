package com.github.kiolk.devto.data.repositories.datasources.network.mappers

import com.github.kiolk.devto.data.repositories.datasources.network.models.FeedUserApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.SearchUserApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.UserApi
import com.github.kiolk.devto.domain.models.User

fun UserApi.toUser(): User {
    return User(
        name = name,
        username = username,
        twitterUsername = twitterUsername,
        githubUsername = githubUsername,
        websiteUrl = websiteUrl,
        profileImage = profileImage,
        profileImage90 = profileImage90 ?: profileImage.orEmpty(),
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

fun SearchUserApi.toUser(): User {
    return User(
        name = user?.name.orEmpty(),
        username = user?.username.orEmpty(),
        twitterUsername = user?.twitterUsername.orEmpty(),
        githubUsername = user?.githubUsername.orEmpty(),
        websiteUrl = user?.websiteUrl.orEmpty(),
        profileImage = user?.profileImage.orEmpty(),
        profileImage90 = user?.profileImage90.orEmpty(),
    )
}
