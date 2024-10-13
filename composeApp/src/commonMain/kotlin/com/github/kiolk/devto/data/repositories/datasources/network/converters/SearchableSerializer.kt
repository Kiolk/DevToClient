package com.github.kiolk.devto.data.repositories.datasources.network.converters

import com.github.kiolk.devto.data.repositories.datasources.network.models.SearchArticleApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.SearchUserApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.SearchableApi
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.json.JsonContentPolymorphicSerializer
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive

object SearchableSerializer :
    JsonContentPolymorphicSerializer<SearchableApi>(SearchableApi::class) {

    override fun selectDeserializer(element: JsonElement): DeserializationStrategy<SearchableApi> {
        val type = element.jsonObject["class_name"]?.jsonPrimitive?.content
        return when (type) {
            "Article" -> SearchArticleApi.serializer()
            "User" -> SearchUserApi.serializer()
            else -> throw IllegalArgumentException("Unknown type: $type")
        }
    }
}
