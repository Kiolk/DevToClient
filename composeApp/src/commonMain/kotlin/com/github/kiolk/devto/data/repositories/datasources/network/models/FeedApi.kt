package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FeedApi(
    @SerialName("title")
    val title: String,
    @SerialName("path")
    val path: String,
    @SerialName("id")
    val id: Int,
    @SerialName("comments_count")
    val commentsCount: Int,
    @SerialName("public_reactions_count")
    val publicReactionCount: Int,
    @SerialName("reading_time")
    val readingTimeMinutes: Int,
    @SerialName("organization")
    val organization: FeedOrganizationApi? = null,
    @SerialName("user")
    val user: FeedUserApi,
    @SerialName("main_image")
    val mainImage: String?,
    @SerialName("tag_list")
    val tagList: List<String>,
    @SerialName("flare_tag")
    val flareTag: FlareTagApi? = null,
    @SerialName("published_timestamp")
    val publishedTimestamp: Instant,
    @SerialName("public_reaction_categories")
    val publicReactionCategories: List<PublicReactionCategoryApi>,
    @SerialName("top_comments")
    val topComments: List<CommentApi> = emptyList()
)
