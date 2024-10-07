package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.datetime.Instant
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CommentApi(
    @SerialName("type_of")
    val typeOf: String,
    @SerialName("id_code")
    val idCode: String,
    @SerialName("created_at")
    val createdAt: Instant,
    @SerialName("body_html")
    val bodyHtml: String,
    @SerialName("user")
    val user: UserApi,
    @SerialName("children")
    val children: List<CommentApi> = emptyList() // For nested comments
)
