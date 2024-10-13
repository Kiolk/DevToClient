package com.github.kiolk.devto.data.repositories.datasources.network.models

import com.github.kiolk.devto.data.repositories.datasources.network.converters.SearchableSerializer
import kotlinx.serialization.Serializable

@Serializable(SearchableSerializer::class)
open class SearchableApi
