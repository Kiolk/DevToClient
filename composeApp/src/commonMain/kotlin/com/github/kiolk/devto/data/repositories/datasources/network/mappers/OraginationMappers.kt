package com.github.kiolk.devto.data.repositories.datasources.network.mappers

import com.github.kiolk.devto.data.repositories.datasources.network.models.FeedOrganizationApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.OrganizationApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.SearchOrganizationApi
import com.github.kiolk.devto.domain.models.Organization

fun OrganizationApi.toOrganization(): Organization {
    return Organization(
        name = name.orEmpty(),
        username = username.orEmpty(),
        slug = slug.orEmpty(),
        profileImage = profileImage.orEmpty(),
        profileImage90 = profileImage90.orEmpty(),
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

fun SearchOrganizationApi.toOrganization(): Organization {
    return Organization(
        name = name.orEmpty(),
        username = "",
        slug = slug.orEmpty(),
        profileImage = profileImage?.url.orEmpty(),
        profileImage90 = profileImage?.url.orEmpty(),
    )
}
