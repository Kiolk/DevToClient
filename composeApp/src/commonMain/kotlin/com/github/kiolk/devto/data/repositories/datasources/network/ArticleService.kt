package com.github.kiolk.devto.data.repositories.datasources.network

import com.github.kiolk.devto.data.repositories.datasources.network.models.ArticleApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.GetArticlesParamsApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

interface ArticleService {

    suspend fun getArticles(params: GetArticlesParamsApi): List<ArticleApi>
}

class ArticleServiceImpl(private val httpClient: HttpClient) : ArticleService {

    override suspend fun getArticles(params: GetArticlesParamsApi): List<ArticleApi> {
        return httpClient.get(GET_ARTICLES_ENDPOINT) {
            params.page?.let { parameter("page", it) }
            params.perPage?.let { parameter("per_page", it) }
            params.tag?.let { parameter("tag", it) }
            params.tags?.let { parameter("tags", it) }
            params.tagsExclude?.let { parameter("tags_exclude", it) }
            params.username?.let { parameter("username", it) }
            params.state?.let { parameter("state", it) }
            params.top?.let { parameter("top", it) }
            params.collectionId?.let { parameter("collection_id", it) }
        }.body()
    }

    companion object {
        private const val GET_ARTICLES_ENDPOINT = "articles"
    }
}
