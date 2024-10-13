package com.github.kiolk.devto.data.repositories.articles

import com.github.kiolk.devto.data.repositories.datasources.network.ArticleService
import com.github.kiolk.devto.data.repositories.datasources.network.mappers.toArticle
import com.github.kiolk.devto.data.repositories.datasources.network.mappers.toFlareTag
import com.github.kiolk.devto.data.repositories.datasources.network.mappers.toGetArticlesParamsApi
import com.github.kiolk.devto.data.repositories.datasources.network.mappers.toOrganization
import com.github.kiolk.devto.data.repositories.datasources.network.mappers.toUser
import com.github.kiolk.devto.data.repositories.datasources.network.models.CommentApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.ReactionApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.SearchArticleApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.SingleArticleApi
import com.github.kiolk.devto.domain.models.Article
import com.github.kiolk.devto.domain.models.Reaction
import com.github.kiolk.devto.domain.models.SearchParameters
import com.github.kiolk.devto.domain.models.Searchable
import com.github.kiolk.devto.presentation.models.Comment
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
        return when (searchParameters.searchType) {
            "Article" -> articleService.search(searchParameters)
                .map {
                    (it as SearchArticleApi).toArticle()
                }

            else -> throw NotImplementedError()
        }
    }
}

private fun CommentApi.mapToComment(): Comment {
    return Comment(
        commentId = idCode,
        userId = user.userId,
        text = bodyHtml,
        publishedTimestamp = createdAt,
        path = "",
        username = user.username,
        name = user.name,
        profileImage90 = user.profileImage ?: user.profileImage90,
    )
}

private fun SingleArticleApi.mapToArticle(): Article {
    return Article(
        id = id,
        slug = slug,
        title = title,
        description = description,
        publishedAt = publishedAt,
        commentsCount = commentsCount,
        publicReactionCount = publicReactionsCount,
        positiveReactionCount = positiveReactionsCount,
        coverImage = coverImage,
        readingTimeMinutes = readingTimeMinutes,
        tagList = tagList.split(","),
        user = user.toUser(),
        organization = organization?.toOrganization(),
        flareTag = flareTag?.toFlareTag(),
    )
}

private fun ReactionApi.toReaction(): Reaction {
    return Reaction(
        result, category, id, reactableId, reactableType,
    )
}

