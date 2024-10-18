package com.github.kiolk.devto.presentation.screens.search.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.github.kiolk.devto.presentation.screens.search.model.TagSearchUi

@Composable
fun TagSearchCard(tag: TagSearchUi) {
    Text(tag.tag.name)
}
