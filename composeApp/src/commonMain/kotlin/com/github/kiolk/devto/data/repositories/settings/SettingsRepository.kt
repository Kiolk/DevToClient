package com.github.kiolk.devto.data.repositories.settings

import kotlinx.coroutines.flow.Flow

interface SettingsRepository {

    val isDarkSystemTheme: Flow<Boolean?>

    fun getToken(): String

    fun setToken(token: String)

    fun onSystemThemeChanged(darkTheme: Boolean)
}
