package com.github.kiolk.devto.domain.usecases

import com.github.kiolk.devto.data.repositories.articles.ArticleRepository
import com.github.kiolk.devto.presentation.models.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetArticleUseCase {
    operator fun invoke(): Flow<List<Article>>
}

class GetArticleUseCaseImpl(private val repository: ArticleRepository) : GetArticleUseCase {

    override fun invoke(): Flow<List<Article>> {
        return flow {
            emit(repository.getArticles())
        }
    }
}
