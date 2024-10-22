package com.github.kiolk.devto.presentation.feed.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import com.github.kiolk.devto.presentation.screens.search.model.TagSearchUi
import com.github.kiolk.devto.presentation.screens.search.view.TagSearchCard

@Composable
fun Header(headerModel: HeaderModel) {
    val searchableUi = headerModel.searchableItem.collectAsState()

    when (val item = searchableUi.value) {
        is TagSearchUi -> TagSearchCard(item)
    }
}
