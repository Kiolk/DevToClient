package com.github.kiolk.devto.presentation.screens.search.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.github.kiolk.devto.presentation.screens.search.model.UserSearchUi

@Composable
fun UserSearchCard(user: UserSearchUi) {
    Text(user.user.username)
}
