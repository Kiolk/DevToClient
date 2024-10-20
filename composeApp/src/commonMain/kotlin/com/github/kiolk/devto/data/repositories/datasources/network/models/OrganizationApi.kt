package com.github.kiolk.devto.data.repositories.datasources.network.models

import com.github.kiolk.devto.data.repositories.datasources.network.converters.ImageSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class OrganizationApi(
    @SerialName("name") val name: String? = null,
    @SerialName("username") val username: String? = null,
    @SerialName("slug") val slug: String? = null,
    @SerialName("profile_image") val profileImage: String? = null,
    @Serializable(ImageSerializer::class)
    @SerialName("profile_image_90") val profileImage90: String? = null,
)
