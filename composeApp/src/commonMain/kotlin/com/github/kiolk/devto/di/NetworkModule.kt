package com.github.kiolk.devto.di

import com.github.kiolk.devto.data.repositories.datasources.network.ArticleService
import com.github.kiolk.devto.data.repositories.datasources.network.ArticleServiceImpl
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

val networkModule = module {
    single<ArticleService> { ArticleServiceImpl(get()) }
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                        useAlternativeNames = false
                    },
                )
            }

            install(Logging) {
                logger = Logger.DEFAULT
                level = LogLevel.ALL // Log everything
            }

            install(HttpTimeout) {
                socketTimeoutMillis = 60_000
                requestTimeoutMillis = 60_000
            }
            defaultRequest {
                header("Content-Type", "application/json")
                header("accept", "application/vnd.forem.api-v1+json")
            }
        }
    }
}
