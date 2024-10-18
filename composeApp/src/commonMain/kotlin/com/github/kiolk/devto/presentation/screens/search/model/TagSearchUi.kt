package com.github.kiolk.devto.presentation.screens.search.model

import com.github.kiolk.devto.domain.models.Tag

data class TagSearchUi(
    val tag: Tag,
) : SearchableUi
