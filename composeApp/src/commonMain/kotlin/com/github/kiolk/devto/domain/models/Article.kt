package com.github.kiolk.devto.domain.models

import Organization
import com.github.kiolk.devto.presentation.models.Comment
import com.github.kiolk.devto.presentation.models.FlareTag
import com.github.kiolk.devto.presentation.models.PublicReactionCategory
import com.github.kiolk.devto.presentation.models.User
import kotlinx.datetime.Instant

data class Article(
    val id: Int,
    val slug: String,
    val title: String,
    val description: String,
    val publishedAt: Instant,
    val commentsCount: Int = 0,
    val publicReactionCount: Int = 0,
    val positiveReactionCount: Int = 0,
    val coverImage: String? = null,
    val readingTimeMinutes: Int = 0,
    val tagList: List<String> = emptyList(),
    val user: User,
    val organization: Organization? = null,
    val flareTag: FlareTag? = null,
    val reactions: List<PublicReactionCategory> = emptyList(),
    val topComments: List<Comment> = emptyList(),
    val comments: List<Comment> = emptyList()
) : Searchable
