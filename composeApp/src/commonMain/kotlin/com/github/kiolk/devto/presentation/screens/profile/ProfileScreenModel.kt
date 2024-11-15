package com.github.kiolk.devto.presentation.screens.profile

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.github.kiolk.devto.domain.usecases.GetFollowAsInSystemUseCase
import com.github.kiolk.devto.domain.usecases.GetUserAppThemeUseCase
import com.github.kiolk.devto.domain.usecases.SetFollowAsInSystemUseCase
import com.github.kiolk.devto.domain.usecases.SetUserAppThemeUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProfileScreenModel(
    private val getUserAppThemeUseCase: GetUserAppThemeUseCase,
    private val setUserAppThemeUseCase: SetUserAppThemeUseCase,
    private val setFollowAsInSystemUseCase: SetFollowAsInSystemUseCase,
    private val getFollowAsInSystemUseCase: GetFollowAsInSystemUseCase,
) : ScreenModel {

    private val _isDarkTheme: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isDarkTheme: StateFlow<Boolean> = _isDarkTheme

    private val _isFollowAsInSystem: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isFollowAsInSystem: StateFlow<Boolean> = _isFollowAsInSystem

    init {
        screenModelScope.launch {
            getUserAppThemeUseCase().collect {
                _isDarkTheme.value = it ?: false
            }
        }
        screenModelScope.launch {
            getFollowAsInSystemUseCase().collect {
                _isFollowAsInSystem.value = it
            }
        }
    }

    fun onDarkThemeChanged(isDarkTheme: Boolean) {
        screenModelScope.launch {
            setUserAppThemeUseCase(isDarkTheme)
            _isDarkTheme.value = isDarkTheme
        }
    }

    fun onFollowAsInSystemChecked(isFollowAsInSystem: Boolean) {
        screenModelScope.launch {
            setFollowAsInSystemUseCase(isFollowAsInSystem)
        }
    }
}
