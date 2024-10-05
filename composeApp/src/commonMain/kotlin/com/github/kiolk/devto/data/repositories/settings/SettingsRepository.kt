package com.github.kiolk.devto.data.repositories.settings

interface SettingsRepository {

    fun getToken(): String

    fun setToken(token: String)
}
