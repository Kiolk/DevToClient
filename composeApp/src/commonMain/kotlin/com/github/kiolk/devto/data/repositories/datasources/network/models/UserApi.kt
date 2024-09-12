package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserApi(
    @SerialName("name") val name: String,
    @SerialName("username") val username: String,
    @SerialName("twitter_username") val twitterUsername: String?,
    @SerialName("github_username") val githubUsername: String?,
    @SerialName("user_id") val userId: Int,
    @SerialName("website_url") val websiteUrl: String? = null,
    @SerialName("profile_image") val profileImage: String,
    @SerialName("profile_image_90") val profileImage90: String,
)
