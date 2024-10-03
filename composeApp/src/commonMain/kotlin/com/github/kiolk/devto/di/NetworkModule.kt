package com.github.kiolk.devto.di

import com.github.kiolk.devto.Secrets
import com.github.kiolk.devto.data.repositories.datasources.network.ArticleService
import com.github.kiolk.devto.data.repositories.datasources.network.ArticleServiceImpl
import com.github.kiolk.devto.data.repositories.datasources.network.converters.InstantSerializer
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.datetime.Instant
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule
import org.koin.core.qualifier.named
import org.koin.dsl.module

private const val BASE_URL = "https://dev.to/"
private const val ACCEPT_HEADER_VALUE = "application/vnd.forem.api-v1+json"
private const val ACCEPT_HEADER = "accept"
private const val CONTENT_TYPE_VALUE = "application/json"
private const val CONTENT_TYPE = "Content-Type"
const val API_TOKEN = "api_token"

val networkModule = module {
    /**
     *TODO add your private API_KEY to Secrets files that doesn't track by git. More info here
    * https://developers.forem.com/api/v1#tag/articles/operation/createArticle:~:text=non%2Dbrowser%20scripts.-,Getting%20an%20API%20key,-To%20obtain%20one
     */
    single<String>(named(API_TOKEN)) { Secrets.API_KEY }
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
