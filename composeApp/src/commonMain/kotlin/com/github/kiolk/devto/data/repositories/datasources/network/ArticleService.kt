package com.github.kiolk.devto.data.repositories.datasources.network

import com.github.kiolk.devto.data.repositories.datasources.network.models.ArticleApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.CommentApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.FeedApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.GetArticlesParamsApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.ReactionApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.SearchResultApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.SearchableApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.SingleArticleApi
import com.github.kiolk.devto.domain.models.SearchParameters
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post

interface ArticleService {

    suspend fun getArticles(params: GetArticlesParamsApi): List<ArticleApi>

    suspend fun getFeed(params: GetArticlesParamsApi): List<FeedApi>

    suspend fun toggleReaction(
        reactionCategory: String,
        articleId: Int,
        reactionOn: String
    ): ReactionApi

    suspend fun getArticleById(id: Int): SingleArticleApi

    suspend fun getCommentsForArticle(articleId: Int): List<CommentApi>

    suspend fun search(searchParameters: SearchParameters): List<SearchableApi>
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

    override suspend fun toggleReaction(
        reactionCategory: String,
        articleId: Int,
        reactionOn: String
    ): ReactionApi {
        val resource = httpClient.post(POST_TOGGLE_REACTION_ENDPOINT) {
            parameter("category", reactionCategory)
            parameter("reactable_id", articleId)
            parameter("reactable_type", reactionOn)
        }

        return resource.body()
    }

    override suspend fun getArticleById(id: Int): SingleArticleApi {
        val article: SingleArticleApi =
            httpClient.get(GET_ARTICLE_BY_ID_ENDPOINT + id.toString()).body()
        return article
    }

    override suspend fun getCommentsForArticle(articleId: Int): List<CommentApi> {
        val comments: List<CommentApi> = httpClient.get(GET_COMMENTS_FOR_ARTICLE_ENDPOINT) {
            parameter("a_id", articleId)
        }.body()
        return comments
    }

    override suspend fun search(searchParameters: SearchParameters): List<SearchableApi> {
        val result: SearchResultApi = httpClient.get(SEARCH_ENDPOINT) {
            parameter("per_page", searchParameters.perPage)
            parameter("page", searchParameters.page)
            parameter("class_name", searchParameters.searchType.value)
            parameter("search_fields", searchParameters.searchField)
        }.body()
        return result.result
    }

    companion object {
        private const val GET_ARTICLES_ENDPOINT = "api/articles"
        private const val GET_FEED_ENDPOINT = "stories/feed/"
        private const val POST_TOGGLE_REACTION_ENDPOINT = "api/reactions/toggle"
        private const val GET_ARTICLE_BY_ID_ENDPOINT = "api/articles/"
        private const val GET_COMMENTS_FOR_ARTICLE_ENDPOINT = "api/comments"
        private const val SEARCH_ENDPOINT = "search/feed_content"
    }
}
