package com.github.kiolk.devto.domain.usecases

import com.github.kiolk.devto.data.repositories.articles.ArticleRepository
import com.github.kiolk.devto.domain.models.Article

interface GetArticleByIdUseCase {

    suspend operator fun invoke(articleId: Int): Article
}

class GetArticleByIdUseCaseImpl(private val articleRepository: ArticleRepository) : GetArticleByIdUseCase {
    override suspend fun invoke(articleId: Int): Article {
        val article = articleRepository.getArticleById(articleId)
        val comments = articleRepository.getCommentsForArticle(articleId)

        return article.copy(comments = comments)
    }
}
