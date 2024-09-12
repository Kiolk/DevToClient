package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ArticleApi(
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("published_at")
    val publishedAt: Instant,
    @SerialName("comments_count")
    val commentsCount: Int = 0,
    @SerialName("public_reactions_count")
    val publicReactionCount: Int = 0,
    @SerialName("positive_reactions_count")
    val positiveReactionCount: Int = 0,
    @SerialName("cover_image")
    val coverImage: String?,
    @SerialName("reading_time_minutes")
    val readingTimeMinutes: Int = 0,
    @SerialName("tag_list")
    val tagList: List<String> = emptyList(),
    @SerialName("user")
    val user: UserApi,
    @SerialName("organization")
    val organization: OrganizationApi? = null,
    @SerialName("flare_tag")
    val flareTag: FlareTagApi? = null,
)
