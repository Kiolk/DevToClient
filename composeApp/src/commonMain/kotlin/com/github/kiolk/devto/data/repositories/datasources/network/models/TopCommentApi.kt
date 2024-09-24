package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TopCommentApi(
    @SerialName("comment_id")
    val commentId: Int,
    @SerialName("user_id")
    val userId: Int,
    @SerialName("published_timestamp")
    val publishedTimestamp: Instant,
    @SerialName("published_at_int")
    val publishedAtInt: Long,
    @SerialName("safe_processed_html")
    val safeProcessedHtml: String
)
