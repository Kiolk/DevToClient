package com.github.kiolk.devto.presentation.screens.tag

import cafe.adriel.voyager.core.model.ScreenModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class TagScreenModel(tagName: String) : ScreenModel {

    private val _tagName = MutableStateFlow(tagName)
    val tagName: StateFlow<String> = _tagName
}
