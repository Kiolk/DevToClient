package com.github.kiolk.devto.data.repositories.datasources.network.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SearchUserApi(
    @SerialName("class_name")
    val className: String? = null,
    @SerialName("id")
    val id: Int? = null,
    @SerialName("path")
    val path: String? = null,
    @SerialName("title")
    val title: String? = null,
    @SerialName("user")
    val user: UserApi? = null
) : SearchableApi()
