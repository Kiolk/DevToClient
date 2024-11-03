package com.github.kiolk.devto.presentation.screens.home.mappers

import com.github.kiolk.devto.domain.models.Article
import com.github.kiolk.devto.domain.models.Comment
import com.github.kiolk.devto.presentation.models.PublicReactionCategory
import com.github.kiolk.devto.presentation.screens.home.models.ArticleUi
import com.github.kiolk.devto.presentation.screens.home.models.CommentUi
import com.github.kiolk.devto.presentation.screens.home.models.ReactionType
import com.github.kiolk.devto.presentation.screens.home.models.ReactionsUi
import com.github.kiolk.devto.utils.localisation.StringProvider
import com.github.kiolk.devto.utils.toPublicationDate
import com.github.kiolk.devto.utils.toPublicationDateAgo

fun Article.mapToArticleUi(stringProvider: StringProvider): ArticleUi {
    return ArticleUi(
        article = this,
        userName = this.user.name,
        organisationName = this.organization?.name,
        publishedAt = this.publishedAt.toPublicationDate(),
        publishedAgo = this.publishedAt.toPublicationDateAgo(stringProvider),
        numberOfComments = this.commentsCount,
        title = this.title,
        description = this.description,
        readingTime = this.readingTimeMinutes,
        tags = this.tagList.map { it.toTagUi(this.flareTag) },
        reactionsUi = ReactionsUi(
            types = this.reactions.sortedBy { it.position }
                .map { it.toReactionType() },
            total = this.publicReactionCount
        ),
        topComments = this.topComments.filter { true }.map { it.toCommentUi(stringProvider) },
        comments = this.comments.map { it.toCommentUi(stringProvider) }
    )
}

fun PublicReactionCategory.toReactionType(): ReactionType {
    return when (this.slug) {
        "like" -> ReactionType.Heart(this.count)
        "exploding_head" -> ReactionType.Head(this.count)
        "unicorn" -> ReactionType.Unicorn(this.count)
        "raised_hands" -> ReactionType.Hands(this.count)
        "fire" -> ReactionType.Fire(this.count)
        else -> ReactionType.Heart(this.count)
    }
}

private fun Comment.toCommentUi(stringProvider: StringProvider): CommentUi {
    return CommentUi(
        id = this.commentId,
        text = this.text.trim(),
        userName = this.username,
        userProfileImage = this.profileImage90,
        commentTime = this.publishedTimestamp.toPublicationDateAgo(stringProvider),
        children = this.children.map { it.toCommentUi(stringProvider) }
    )
}
