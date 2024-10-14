package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchCommentApi(
    @SerialName("id")
    val id: String? = null,
    @SerialName("path")
    val path: String? = null,
    @SerialName("public_reactions_count")
    val publicReactionsCount: Int? = null,
    @SerialName("body_text")
    val bodyText: String? = null,
    @SerialName("class_name")
    val className: String? = null,
    @SerialName("highlight")
    val highlight: HighlightApi? = null,
    @SerialName("hotness_score")
    val hotnessScore: Int? = null,
    @SerialName("published")
    val published: Boolean? = null,
    @SerialName("published_at")
    val publishedAt: Instant? = null,
    @SerialName("readable_publish_date_string")
    val readablePublishDateString: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("user")
    val user: UserApi? = null
) : SearchableApi()

@Serializable
data class HighlightApi(
    @SerialName("body_text")
    val bodyText: List<String?>? = null
)
