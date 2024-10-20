package com.github.kiolk.devto.data.repositories.datasources.network.converters

import io.ktor.http.decodeURLPart
import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object ImageSerializer : KSerializer<String> {
    override val descriptor: SerialDescriptor =
        String.serializer().descriptor

    override fun deserialize(decoder: Decoder): String {
        return decoder.decodeString().cleanUpImageUrl()
    }

    override fun serialize(encoder: Encoder, value: String) {
        encoder.encodeString(value)
    }
}

private fun String.cleanUpImageUrl(): String {
    val firstIndex = this.indexOf("http")
    if (firstIndex != -1) {
        val secondIndex = this.indexOf("http", firstIndex + 1)
        if (secondIndex != -1) {
            return this.substring(secondIndex).decodeURLPart()
        }
    }
    return this.decodeURLPart()
}
