package com.github.kiolk.devto.data.repositories.datasources.network.models

import com.github.kiolk.devto.data.repositories.datasources.network.converters.ImageSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserApi(
    @SerialName("name") val name: String = "",
    @SerialName("username") val username: String = "",
    @SerialName("twitter_username") val twitterUsername: String? = null,
    @SerialName("github_username") val githubUsername: String? = null,
    @SerialName("user_id") val userId: Int? = null,
    @SerialName("website_url") val websiteUrl: String? = null,
    @SerialName("profile_image") val profileImage: String? = null,
    @Serializable(ImageSerializer::class)
    @SerialName("profile_image_90")
    val profileImage90: String? = null,
)
