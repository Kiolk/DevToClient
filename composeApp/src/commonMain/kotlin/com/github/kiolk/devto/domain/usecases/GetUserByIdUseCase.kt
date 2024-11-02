package com.github.kiolk.devto.domain.usecases

import com.github.kiolk.devto.data.repositories.articles.ArticleRepository
import com.github.kiolk.devto.domain.models.User

interface GetUserByIdUseCase {

    suspend operator fun invoke(userId: Int): User
}

class GetUserByIdUseCaseImpl(private val articleRepository: ArticleRepository) :
    GetUserByIdUseCase {

    override suspend fun invoke(userId: Int): User {
        return articleRepository.getUserById(userId)
    }
}
