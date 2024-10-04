package com.github.kiolk.devto.data.repositories.datasources.network

import com.github.kiolk.devto.data.repositories.datasources.network.models.ArticleApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.FeedApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.GetArticlesParamsApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.ReactionApi
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post

interface ArticleService {

    suspend fun getArticles(params: GetArticlesParamsApi): List<ArticleApi>

    suspend fun getFeed(params: GetArticlesParamsApi): List<FeedApi>

    suspend fun toggleReaction(reactionCategory: String, articleId: Int, reactionOn: String): ReactionApi
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

    override suspend fun getFeed(params: GetArticlesParamsApi): List<FeedApi> {
        return httpClient.get(GET_FEED_ENDPOINT + params.sortingType.value) {
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

    override suspend fun toggleReaction(reactionCategory: String, articleId: Int, reactionOn: String): ReactionApi {
        val resource = httpClient.post(POST_TOGGLE_REACtION_ENDPOINT) {
            parameter("category", reactionCategory)
            parameter("reactable_id", articleId)
            parameter("reactable_type", reactionOn)
        }


        return resource.body()
    }

    companion object {
        private const val GET_ARTICLES_ENDPOINT = "api/articles"
        private const val GET_FEED_ENDPOINT = "stories/feed/"
        private const val POST_TOGGLE_REACtION_ENDPOINT = "api/reactions/toggle"
    }
}
