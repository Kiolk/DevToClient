package com.github.kiolk.devto.data.repositories.settings

import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SettingsRepositoryImpl(private val settings: Settings, private val defaultToken: String) :
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

    companion object {
        private const val SETTINGS_TOKEN = "settings_token"
    }
}
