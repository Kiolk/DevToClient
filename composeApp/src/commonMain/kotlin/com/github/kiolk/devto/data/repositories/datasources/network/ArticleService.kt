package com.github.kiolk.devto.data.repositories.datasources.network

import com.github.kiolk.devto.data.repositories.datasources.network.models.ArticleApi
import io.ktor.client.HttpClient
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json

interface ArticleService {

    suspend fun getArticles(): List<ArticleApi>
}

class ArticleServiceImpl(private val httpClient: HttpClient) : ArticleService {

    override suspend fun getArticles(): List<ArticleApi> {
        val response = httpClient.get("$BASE_URL$GET_ARTICLES_ENDPOINT") { HttpRequestBuilder() }.bodyAsText()
        return Json { ignoreUnknownKeys = true }.decodeFromString<List<ArticleApi>>(response)
    }

    companion object {
        private val BASE_URL = "https://dev.to/api/"
        private val GET_ARTICLES_ENDPOINT = "articles"
    }
}
