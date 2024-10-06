package com.github.kiolk.devto.data.repositories.datasources.network.plugins

import com.github.kiolk.devto.data.repositories.settings.SettingsRepository
import io.ktor.client.plugins.api.ClientPlugin
import io.ktor.client.plugins.api.createClientPlugin

interface AuthenticationPlugin {
    fun createPlugin(): ClientPlugin<Unit>
}

class AuthenticationPluginImpl(private val settingsRepository: SettingsRepository) : AuthenticationPlugin {

    override fun createPlugin(): ClientPlugin<Unit> {
        val token = settingsRepository.getToken()
        return createClientPlugin(PLUGIN_NAME) {
            onRequest { request, _ ->
                request.headers.append(API_KEY, token)
            }
        }
    }

    private companion object {
        const val API_KEY = "api-key"
        const val PLUGIN_NAME = "AuthenticationPlugin"
    }
}
