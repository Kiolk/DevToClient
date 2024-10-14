package com.github.kiolk.devto.data.repositories.datasources.network.mappers

import com.github.kiolk.devto.data.repositories.datasources.network.models.SearchTagApi
import com.github.kiolk.devto.domain.models.Tag

fun SearchTagApi.toTag(): Tag {
    return Tag(
        id = id ?: 0,
        name = name.orEmpty(),
        hotnessScore = hotnessScore ?: 0,
        supported = supported ?: false,
        shortSummary = shortSummary.orEmpty(),
        rulesHtml = rulesHtml.orEmpty(),
        bgColorHex = bgColorHex ?: "#FFFFFF",
        badgeImageUrl = badge?.badgeImage?.url.orEmpty(),
    )
}
