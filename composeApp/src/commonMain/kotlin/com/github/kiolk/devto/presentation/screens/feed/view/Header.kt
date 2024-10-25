package com.github.kiolk.devto.presentation.screens.feed.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.github.kiolk.devto.presentation.screens.search.model.TagSearchUi
import com.github.kiolk.devto.presentation.screens.search.model.UserSearchUi

@Composable
fun Header(headerModel: HeaderScreenModel) {
    val searchableUi = headerModel.searchableItem.collectAsState()

    when (val item = searchableUi.value) {
        is TagSearchUi -> TagHeader(item)
        is UserSearchUi -> UserHeader(item)
    }
}
