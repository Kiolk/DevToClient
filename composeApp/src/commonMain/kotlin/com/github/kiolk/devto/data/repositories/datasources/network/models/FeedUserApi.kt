package com.github.kiolk.devto.data.repositories.datasources.network.models

import com.github.kiolk.devto.data.repositories.datasources.network.converters.ImageSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedUserApi(
    @SerialName("name") val name: String,
    @SerialName("username") val username: String,
    @SerialName("profile_image_url") val profileImageUrl: String,
    @Serializable(ImageSerializer::class)
    @SerialName("profile_image_90") val profileImage90: String,
    @SerialName("slug") val slug: String,
)
