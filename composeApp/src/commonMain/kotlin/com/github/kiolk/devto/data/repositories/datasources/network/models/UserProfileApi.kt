package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UserProfileApi(
    @SerialName("type_of")
    val typeOf: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("username")
    val username: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("twitter_username")
    val twitterUsername: String? = null,
    @SerialName("github_username")
    val githubUsername: String? = null,
    @SerialName("summary")
    val summary: String? = null,
    @SerialName("location")
    val location: String? = null,
    @SerialName("website_url")
    val websiteUrl: String? = null,
    @SerialName("joined_at")
    val joinedAt: String? = null,
    @SerialName("profile_image")
    val profileImage: String? = null
)
