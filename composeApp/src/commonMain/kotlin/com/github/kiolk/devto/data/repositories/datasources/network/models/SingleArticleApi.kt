package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SingleArticleApi(
    @SerialName("type_of")
    val typeOf: String,
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("readable_publish_date")
    val readablePublishDate: String,
    @SerialName("slug")
    val slug: String,
    @SerialName("path")
    val path: String,
    @SerialName("url")
    val url: String,
    @SerialName("comments_count")
    val commentsCount: Int,
    @SerialName("public_reactions_count")
    val publicReactionsCount: Int,
    @SerialName("collection_id")
    val collectionId: Int? = null,
    @SerialName("published_timestamp")
    val publishedTimestamp: Instant,
    @SerialName("positive_reactions_count")
    val positiveReactionsCount: Int,
    @SerialName("cover_image")
    val coverImage: String,
    @SerialName("social_image")
    val socialImage: String,
    @SerialName("canonical_url")
    val canonicalUrl: String,
    @SerialName("created_at")
    val createdAt: Instant,
    @SerialName("edited_at")
    val editedAt: Instant? = null,
    @SerialName("crossposted_at")
    val crosspostedAt: Instant? = null,
    @SerialName("published_at")
    val publishedAt: Instant,
    @SerialName("last_comment_at")
    val lastCommentAt: Instant,
    @SerialName("reading_time_minutes")
    val readingTimeMinutes: Int,
    @SerialName("tag_list")
    val tagList: String,
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("body_html")
    val bodyHtml: String,
    @SerialName("body_markdown")
    val bodyMarkdown: String,
    @SerialName("user")
    val user: UserApi,
    @SerialName("flare_tag")
    val flareTag: FlareTagApi? = null
)
