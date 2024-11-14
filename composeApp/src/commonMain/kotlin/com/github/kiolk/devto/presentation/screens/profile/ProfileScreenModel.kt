package com.github.kiolk.devto.presentation.screens.profile

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.github.kiolk.devto.domain.usecases.GetUserAppThemeUseCase
import com.github.kiolk.devto.domain.usecases.SetUserAppThemeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileScreenModel(
    private val getUserAppThemeUseCase: GetUserAppThemeUseCase,
    private val setUserAppThemeUseCase: SetUserAppThemeUseCase,
) : ScreenModel {

    private val _isDarkTheme: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme

    init {
        screenModelScope.launch {
            getUserAppThemeUseCase().collect {
                _isDarkTheme.value = it ?: false
            }
        }
    }

    fun onDarkThemeChanged(isDarkTheme: Boolean) {
        screenModelScope.launch {
            setUserAppThemeUseCase(isDarkTheme)
            _isDarkTheme.value = isDarkTheme
        }
    }
}
