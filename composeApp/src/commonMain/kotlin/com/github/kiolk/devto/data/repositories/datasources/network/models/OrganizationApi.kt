package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrganizationApi(
    @SerialName("name") val name: String,
    @SerialName("username") val username: String,
    @SerialName("slug") val slug: String,
    @SerialName("profile_image") val profileImage: String,
    @SerialName("profile_image_90") val profileImage90: String,
)
