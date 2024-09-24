package com.github.kiolk.devto.di

import com.github.kiolk.devto.data.repositories.datasources.network.ArticleService
import com.github.kiolk.devto.data.repositories.datasources.network.ArticleServiceImpl
import com.github.kiolk.devto.data.repositories.datasources.network.converters.InstantSerializer
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpResponseValidator
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.client.statement.HttpResponse
import io.ktor.serialization.kotlinx.json.json
import kotlinx.datetime.Instant
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.koin.dsl.module

private const val BASE_URL = "https://dev.to/"
private const val ACCEPT_HEADER_VALUE = "application/vnd.forem.api-v1+json"
private const val ACCEPT_HEADER = "accept"
private const val CONTENT_TYPE_VALUE = "application/json"
private const val CONTENT_TYPE = "Content-Type"

val networkModule = module {
    single<ArticleService> { ArticleServiceImpl(get()) }
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        useAlternativeNames = false
                        serializersModule = SerializersModule {
                            contextual(Instant::class, InstantSerializer)
                        }
                    },
                )
            }
            defaultRequest {
                url(BASE_URL)
                header(CONTENT_TYPE, CONTENT_TYPE_VALUE)
                header(ACCEPT_HEADER, ACCEPT_HEADER_VALUE)
            }
            install(Logging) {
                level = LogLevel.ALL
            }

            install(HttpTimeout) {
                socketTimeoutMillis = 60_000
                requestTimeoutMillis = 60_000
            }
        }
    }
}
