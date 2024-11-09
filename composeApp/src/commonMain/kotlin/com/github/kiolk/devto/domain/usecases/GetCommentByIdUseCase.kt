package com.github.kiolk.devto.domain.usecases

import com.github.kiolk.devto.data.repositories.articles.ArticleRepository
import com.github.kiolk.devto.domain.models.Comment

interface GetCommentByIdUseCase {

    suspend operator fun invoke(commentId: String): Comment
}

class GetCommentByIdUseCaseImpl(private val articleRepository: ArticleRepository) : GetCommentByIdUseCase {

    override suspend fun invoke(commentId: String): Comment {
        return articleRepository.getCommentById(commentId)
    }
}
