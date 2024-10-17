package com.github.kiolk.devto.presentation.screens.search.model

import com.github.kiolk.devto.domain.models.User

data class UserSearchUi(
    val user: User,
) : SearchableUi
