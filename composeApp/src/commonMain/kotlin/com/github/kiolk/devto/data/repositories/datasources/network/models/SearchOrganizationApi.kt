package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchOrganizationApi(
    @SerialName("class_name")
    val className: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("summary")
    val summary: String? = null,
    @SerialName("profile_image")
    val profileImage: ProfileImageApi? = null,
    @SerialName("twitter_username")
    val twitterUsername: String? = null,
    @SerialName("slug")
    val slug: String? = null,
) : SearchableApi()

@Serializable
data class ProfileImageApi(
    @SerialName("url")
    val url: String? = null,
)
