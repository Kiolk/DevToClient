package com.github.kiolk.devto.data.repositories.settings

import com.russhwolf.settings.Settings

class SettingsRepositoryImpl(private val settings: Settings, private val defaultToken: String) : SettingsRepository {
    override fun getToken(): String {
        return settings.getString(SETTINGS_TOKEN, defaultToken)
    }

    override fun setToken(token: String) {
        settings.putString(SETTINGS_TOKEN, token)
    }

    companion object {
        private const val SETTINGS_TOKEN = "settings_token"
    }
}
