package com.github.kiolk.devto.presentation.feed

import com.github.kiolk.devto.domain.models.SearchType
import com.github.kiolk.devto.domain.usecases.GetArticleUseCase
import com.github.kiolk.devto.domain.usecases.ToggleReactionUseCase
import com.github.kiolk.devto.presentation.screens.home.HomeScreenModel
import com.github.kiolk.devto.utils.localisation.StringProvider

class FeedScreenModel(
    getArticlesUseCase: GetArticleUseCase,
    stringProvider: StringProvider,
    toggleReactionUseCase: ToggleReactionUseCase
) : HomeScreenModel(getArticlesUseCase, stringProvider, toggleReactionUseCase) {

    override val type: SearchType = SearchType.Tag
}
