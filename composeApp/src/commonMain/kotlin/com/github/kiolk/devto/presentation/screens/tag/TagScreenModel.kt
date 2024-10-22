package com.github.kiolk.devto.presentation.screens.tag

import com.github.kiolk.devto.domain.models.SearchType
import com.github.kiolk.devto.domain.models.Tag
import com.github.kiolk.devto.domain.usecases.GetArticleUseCase
import com.github.kiolk.devto.domain.usecases.ToggleReactionUseCase
import com.github.kiolk.devto.presentation.screens.home.HomeScreenModel
import com.github.kiolk.devto.presentation.screens.search.mapper.mapToTagUi
import com.github.kiolk.devto.presentation.screens.search.model.TagSearchUi
import com.github.kiolk.devto.utils.localisation.StringProvider
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TagScreenModel(
    private val tag: Tag,
    getArticlesUseCase: GetArticleUseCase,
    private val stringProvider: StringProvider,
    toggleReactionUseCase: ToggleReactionUseCase
) : HomeScreenModel(getArticlesUseCase, stringProvider, toggleReactionUseCase) {

    override val type: SearchType = SearchType.Tag

    private val _tagName = MutableStateFlow(tag.mapToTagUi())
    val tagName: StateFlow<TagSearchUi> = _tagName
}
