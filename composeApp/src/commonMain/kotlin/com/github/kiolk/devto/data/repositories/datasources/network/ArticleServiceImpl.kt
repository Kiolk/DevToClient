package com.github.kiolk.devto.data.repositories.datasources.network

import com.github.kiolk.devto.data.repositories.datasources.network.mappers.toSortBy
import com.github.kiolk.devto.data.repositories.datasources.network.mappers.toTime
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

class ArticleServiceImpl(private val httpClient: HttpClient) : ArticleService {

    override suspend fun getArticles(params: GetArticlesParamsApi): List<ArticleApi> {
        return httpClient.get(GET_ARTICLES_ENDPOINT) {
            params.page?.let { parameter(PAGE_PARAM, it) }
            params.perPage?.let { parameter(PER_PAGE_PARAM, it) }
            params.tag?.let { parameter(TAG_PARAM, it) }
        }.body()
    }

    override suspend fun getFeed(params: GetArticlesParamsApi): List<FeedApi> {
        return httpClient.get(GET_FEED_ENDPOINT + params.sortingType.value) {
            params.page?.let { parameter(PAGE_PARAM, it) }
            params.perPage?.let { parameter(PER_PAGE_PARAM, it) }
            params.tag?.let { parameter(TAG_PARAM, it) }
            params.type?.let { parameter(CLASS_NAME_PARAM, it.value) }
            params.tagName?.let { parameter(TAG_PARAM, it) }
        }.body()
    }

    override suspend fun toggleReaction(
        reactionCategory: String,
        articleId: Int,
        reactionOn: String
    ): ReactionApi {
        val resource = httpClient.post(POST_TOGGLE_REACTION_ENDPOINT) {
            parameter(CATEGORY_PARAM, reactionCategory)
            parameter(REACTABLE_ID_PARAM, articleId)
            parameter(REACTABLE_TYPE_PARAM, reactionOn)
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
            parameter(ARTICLE_ID_PARAM, articleId)
        }.body()
        return comments
    }

    override suspend fun search(searchParameters: SearchParameters): List<SearchableApi> {
        val result: SearchResultApi = httpClient.get(SEARCH_ENDPOINT) {
            parameter(PER_PAGE_PARAM, searchParameters.perPage)
            parameter(PAGE_PARAM, searchParameters.page)
            parameter(CLASS_NAME_PARAM, searchParameters.searchType.value)
            parameter(SEARCH_FIELD_PARAM, searchParameters.searchField)
            searchParameters.tag?.let {
                parameter(TAG_PARAM, it)
            }
            searchParameters.tagNames?.let {
                // TODO investigate how correctly pass tags parameter
                parameter(TAG_NAMES_PARAM, it.joinToString(","))
            }
            searchParameters.sort?.let { sortType ->
                parameter(SORT_DIRECTION_PARAM, sortType.value)
            }
            parameter(SORT_BY_PARAM, searchParameters.sortingType.toSortBy())
            searchParameters.sortingType.toTime()?.let {
                parameter(PUBLISHED_AT_PARAM, it)
            }
        }.body()
        return result.result
    }

    private companion object {
        const val GET_ARTICLES_ENDPOINT = "api/articles"
        const val GET_FEED_ENDPOINT = "stories/feed/"
        const val POST_TOGGLE_REACTION_ENDPOINT = "api/reactions/toggle"
        const val GET_ARTICLE_BY_ID_ENDPOINT = "api/articles/"
        const val GET_COMMENTS_FOR_ARTICLE_ENDPOINT = "api/comments"
        const val SEARCH_ENDPOINT = "search/feed_content"
        const val PUBLISHED_AT_PARAM = "published_at[gte]"
        const val SORT_BY_PARAM = "sort_by"
        const val SORT_DIRECTION_PARAM = "sort_direction"
        const val TAG_NAMES_PARAM = "tag_names[]"
        const val TAG_PARAM = "tag"
        const val SEARCH_FIELD_PARAM = "search_fields"
        const val CLASS_NAME_PARAM = "class_name"
        const val PAGE_PARAM = "page"
        const val PER_PAGE_PARAM = "per_page"
        const val CATEGORY_PARAM = "category"
        const val REACTABLE_ID_PARAM = "reactable_id"
        const val REACTABLE_TYPE_PARAM = "reactable_type"
        const val ARTICLE_ID_PARAM = "a_id"
    }
}
