package com.github.kiolk.devto.domain.usecases

import com.github.kiolk.devto.data.repositories.articles.ArticleRepository
import com.github.kiolk.devto.presentation.models.Article
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll

interface GetArticleByIdUseCase {

    suspend operator fun invoke(articleId: Int): Article
}

class GetArticlesUseCaseImpl(private val articleRepository: ArticleRepository) : GetArticleByIdUseCase {
    override suspend fun invoke(articleId: Int): Article {
        val (article, comments) = awaitAll(
            async { articleRepository.getArticleById(articleId) },
            async { articleRepository.getCommentsForArticle(articleId) }) articleRepository . getCommentsForArticle (articleId) )

        return article
    }
}
