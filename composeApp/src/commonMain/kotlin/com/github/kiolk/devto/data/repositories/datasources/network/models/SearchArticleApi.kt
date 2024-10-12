package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchArticleApi(
    @SerialName("class_name")
    val className: String? = null,
    @SerialName("cloudinary_video_url")
    val cloudinaryVideoUrl: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("path")
    val path: String? = null,
    @SerialName("public_reactions_count")
    val publicReactionsCount: Int? = null,
    @SerialName("readable_publish_date")
    val readablePublishDate: String? = null,
    @SerialName("reading_time")
    val readingTime: Int? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("user_id")
    val userId: Int? = null,
    @SerialName("public_reaction_categories")
    val publicReactionCategories: List<PublicReactionCategoryApi>? = null,
    @SerialName("comments_count")
    val commentsCount: Int? = null,
    @SerialName("video_duration_string")
    val videoDurationString: String? = null,
    @SerialName("published_at_int")
    val publishedAtInt: Long? = null,
    @SerialName("tag_list")
    val tagList: List<String>? = null,
    @SerialName("flare_tag")
    val flareTag: FlareTagApi? = null,
    @SerialName("user")
    val user: UserApi? = null,
    @SerialName("organization")
    val organization: OrganizationApi? = null
)
