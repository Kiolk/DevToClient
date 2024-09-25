package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedOrganizationApi(
    @SerialName("name") val name: String,
    @SerialName("username") val username: String,
    @SerialName("slug") val slug: String,
    @SerialName("profile_image_90") val profileImage90: String,
    @SerialName("profile_image_url") val profileImageUrl: String,
)
