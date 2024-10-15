package com.github.kiolk.devto.data.repositories.articles

import com.github.kiolk.devto.data.repositories.datasources.network.ArticleService
import com.github.kiolk.devto.data.repositories.datasources.network.mappers.mapToArticle
import com.github.kiolk.devto.data.repositories.datasources.network.mappers.mapToComment
import com.github.kiolk.devto.data.repositories.datasources.network.mappers.toArticle
import com.github.kiolk.devto.data.repositories.datasources.network.mappers.toComment
import com.github.kiolk.devto.data.repositories.datasources.network.mappers.toGetArticlesParamsApi
import com.github.kiolk.devto.data.repositories.datasources.network.mappers.toOrganization
import com.github.kiolk.devto.data.repositories.datasources.network.mappers.toTag
import com.github.kiolk.devto.data.repositories.datasources.network.mappers.toUser
import com.github.kiolk.devto.data.repositories.datasources.network.models.ReactionApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.SearchArticleApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.SearchCommentApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.SearchOrganizationApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.SearchTagApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.SearchUserApi
import com.github.kiolk.devto.domain.models.Article
import com.github.kiolk.devto.domain.models.Comment
import com.github.kiolk.devto.domain.models.Reaction
import com.github.kiolk.devto.domain.models.SearchParameters
import com.github.kiolk.devto.domain.models.SearchType
import com.github.kiolk.devto.domain.models.Searchable
import com.github.kiolk.devto.presentation.models.GetArticlesParams

class ArticleRepositoryImpl(private val articleService: ArticleService) : ArticleRepository {
    override suspend fun getArticles(params: GetArticlesParams): List<Article> {
        return articleService.getFeed(params.toGetArticlesParamsApi()).map { it.toArticle() }
    }

    override suspend fun toggleReaction(
        reactionCategory: String,
        articleId: Int,
        reactionOn: String
    ): Reaction {
        return articleService.toggleReaction(reactionCategory, articleId, reactionOn).toReaction()
    }

    override suspend fun getArticleById(id: Int): Article {
        return articleService.getArticleById(id).mapToArticle()
    }

    override suspend fun getCommentsForArticle(id: Int): List<Comment> {
        return articleService.getCommentsForArticle(id).map { it.mapToComment() }
    }

    override suspend fun search(searchParameters: SearchParameters): List<Searchable> {
        val responseBody = articleService.search(searchParameters)
        return when (searchParameters.searchType) {
            SearchType.Article -> responseBody.map {
                (it as SearchArticleApi).toArticle()
            }

            SearchType.User -> responseBody.map {
                (it as SearchUserApi).toUser()
            }

            SearchType.Organization -> responseBody.map {
                (it as SearchOrganizationApi).toOrganization()
            }

            SearchType.Comment -> responseBody.map {
                (it as SearchCommentApi).toComment()
            }

            SearchType.Tag -> responseBody.map {
                (it as SearchTagApi).toTag()
            }
        }
    }
}

private fun ReactionApi.toReaction(): Reaction {
    return Reaction(
        result, category, id, reactableId, reactableType,
    )
}

