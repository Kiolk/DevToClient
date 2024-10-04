package com.github.kiolk.devto.domain.usecases

import com.github.kiolk.devto.data.repositories.articles.ArticleRepository
import com.github.kiolk.devto.domain.models.Reaction

interface ToggleReactionUseCase {

    suspend operator fun invoke(reactionCategory: String, articleId: Int, reactionOn: String): Reaction
}

class ToggleReactionUseCaseImpl(private val repository: ArticleRepository): ToggleReactionUseCase {
    override suspend fun invoke(reactionCategory: String, articleId: Int, reactionOn: String): Reaction {
        return repository.toggleReaction(reactionCategory, articleId, reactionOn)
    }
}
