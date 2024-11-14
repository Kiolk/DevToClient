package com.github.kiolk.devto.data.repositories.settings

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ObservableSettings
import com.russhwolf.settings.coroutines.getBooleanOrNullFlow
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsRepositoryImpl(private val settings: ObservableSettings, private val defaultToken: String) :
    SettingsRepository {
    private val _isDarkSystemTheme = MutableStateFlow<Boolean?>(null)
    override val isDarkSystemTheme: Flow<Boolean?> = _isDarkSystemTheme.asStateFlow()

    override fun getToken(): String {
        return settings.getString(SETTINGS_TOKEN, defaultToken)
    }

    override fun setToken(token: String) {
        settings.putString(SETTINGS_TOKEN, token)
    }

    override fun onSystemThemeChanged(darkTheme: Boolean) {
        _isDarkSystemTheme.value = darkTheme
    }

    @OptIn(ExperimentalSettingsApi::class)
    override fun isUserAppThemeDark(): Flow<Boolean?> {
        val isDark = settings.getBooleanOrNullFlow(IS_DARK_THEM)
        return isDark
    }

    override fun setUserAppThemeDark(isDarkTheme: Boolean) {
        settings.putBoolean(IS_DARK_THEM, isDarkTheme)
    }

    companion object {
        private const val SETTINGS_TOKEN = "settings_token"
        private const val IS_DARK_THEM = "is_dark_theme"
    }
}
