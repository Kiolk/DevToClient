package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SingleArticleApi(
    @SerialName("type_of")
    val typeOf: String? = null,
    @SerialName("id")
    val id: Int,
    @SerialName("title")
    val title: String? = null,
    @SerialName("description")
    val description: String? = null,
    @SerialName("readable_publish_date")
    val readablePublishDate: String? = null,
    @SerialName("slug")
    val slug: String? = null,
    @SerialName("path")
    val path: String? = null,
    @SerialName("url")
    val url: String? = null,
    @SerialName("comments_count")
    val commentsCount: Int? = null,
    @SerialName("public_reactions_count")
    val publicReactionsCount: Int? = null,
    @SerialName("collection_id")
    val collectionId: Int? = null,
    @SerialName("published_timestamp")
    val publishedTimestamp: Instant? = null,
    @SerialName("positive_reactions_count")
    val positiveReactionsCount: Int? = null,
    @SerialName("cover_image")
    val coverImage: String? = null,
    @SerialName("social_image")
    val socialImage: String? = null,
    @SerialName("canonical_url")
    val canonicalUrl: String? = null,
    @SerialName("created_at")
    val createdAt: Instant? = null,
    @SerialName("edited_at")
    val editedAt: Instant? = null,
    @SerialName("crossposted_at")
    val crosspostedAt: Instant? = null,
    @SerialName("published_at")
    val publishedAt: Instant? = null,
    @SerialName("last_comment_at")
    val lastCommentAt: Instant? = null,
    @SerialName("reading_time_minutes")
    val readingTimeMinutes: Int? = null,
    @SerialName("tag_list")
    val tagList: String? = null,
    @SerialName("tags")
    val tags: List<String>? = null,
    @SerialName("body_html")
    val bodyHtml: String? = null,
    @SerialName("body_markdown")
    val bodyMarkdown: String? = null,
    @SerialName("user")
    val user: UserApi? = null,
    @SerialName("flare_tag")
    val flareTag: FlareTagApi? = null,
    @SerialName("organization")
    val organization: OrganizationApi? = null,
)
