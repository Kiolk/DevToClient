package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlareTagApi(
    @SerialName("name") val name: String,
    @SerialName("bg_color_hex") val bgColorHex: String,
    @SerialName("text_color_hex") val textColorHex: String,
)
