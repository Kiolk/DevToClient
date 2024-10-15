package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchTagApi(
    @SerialName("class_name")
    val className: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("hotness_score")
    val hotnessScore: Int? = null,
    @SerialName("supported")
    val supported: Boolean? = null,
    @SerialName("short_summary")
    val shortSummary: String? = null,
    @SerialName("rules_html")
    val rulesHtml: String? = null,
    @SerialName("bg_color_hex")
    val bgColorHex: String? = null,
    @SerialName("badge")
    val badge: BadgeApi? = null,
) : SearchableApi()

@Serializable
data class BadgeApi(
    @SerialName("badge_image")
    val badgeImage: BadgeImageApi? = null
)

@Serializable
data class BadgeImageApi(
    @SerialName("url")
    val url: String? = null
)
