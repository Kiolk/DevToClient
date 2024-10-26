package com.github.kiolk.devto.presentation.screens.feed.view

import androidx.compose.runtime.Composable
import com.github.kiolk.devto.presentation.screens.search.model.UserSearchUi
import com.github.kiolk.devto.presentation.screens.search.view.UserSearchCard

@Composable
fun UserHeader(user: UserSearchUi) {
    UserSearchCard(user)
}
