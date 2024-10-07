package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopCommentApi(
    @SerialName("comment_id") val commentId: Int,
    @SerialName("user_id") val userId: Int,
    @SerialName("published_timestamp") val publishedTimestamp: Instant,
    @SerialName("path") val path: String,
    @SerialName("username") val username: String,
    @SerialName("name") val name: String,
    @SerialName("profile_image_90") val profileImage90: String,
    @SerialName("safe_processed_html") val safeProcessedHtml: String,
)
