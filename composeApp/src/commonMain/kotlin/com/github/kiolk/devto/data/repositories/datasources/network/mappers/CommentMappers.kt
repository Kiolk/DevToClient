package com.github.kiolk.devto.data.repositories.datasources.network.mappers

import com.github.kiolk.devto.data.repositories.datasources.network.models.CommentApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.SearchCommentApi
import com.github.kiolk.devto.data.repositories.datasources.network.models.TopCommentApi
import com.github.kiolk.devto.domain.models.Comment
import kotlinx.datetime.Clock

fun TopCommentApi.toComment(): Comment {
    return Comment(
        commentId = this.commentId.toString(),
        userId = this.userId,
        publishedTimestamp = this.publishedTimestamp, // Format Instant as needed
        path = this.path,
        username = this.username,
        name = this.name,
        profileImage90 = this.profileImage90,
        text = this.safeProcessedHtml
    )
}

fun SearchCommentApi.toComment(): Comment {
    return Comment(
        commentId = id.orEmpty(),
        userId = user?.userId,
        publishedTimestamp = publishedAt ?: Clock.System.now(), // Format Instant as needed
        path = path.orEmpty(),
        username = user?.username.orEmpty(),
        name = user?.name.orEmpty(),
        profileImage90 = user?.profileImage90.orEmpty(),
        text = bodyText.orEmpty(),
    )
}

fun CommentApi.mapToComment(): Comment {
    return Comment(
        commentId = idCode,
        userId = user.userId,
        text = bodyHtml,
        publishedTimestamp = createdAt,
        path = "",
        username = user.username,
        name = user.name,
        profileImage90 = user.profileImage ?: user.profileImage90.orEmpty(),
    )
}
