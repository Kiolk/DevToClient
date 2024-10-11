package com.github.kiolk.devto.domain.usecases

import com.github.kiolk.devto.data.repositories.articles.ArticleRepository
import com.github.kiolk.devto.domain.models.SearchParameters
import com.github.kiolk.devto.domain.models.Searchable

interface SearchUseCase {

    suspend operator fun invoke(searchParameters: SearchParameters): List<Searchable>
}

class SearchUseCaseImpl(private val articleRepository: ArticleRepository) : SearchUseCase {

    override suspend fun invoke(searchParameters: SearchParameters): List<Searchable> {
        return articleRepository.search(searchParameters)
    }
}
