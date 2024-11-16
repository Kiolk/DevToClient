package com.github.kiolk.devto.domain.usecases

import com.github.kiolk.devto.data.repositories.settings.SettingsRepository

interface SetUserAppThemeUseCase {
    suspend operator fun invoke(isDarkTheme: Boolean)
}

class SetUserAppThemeUseCaseImpl(private val settingsRepository: SettingsRepository) :
    SetUserAppThemeUseCase {
    override suspend fun invoke(isDarkTheme: Boolean) {
        settingsRepository.setUserAppThemeDark(isDarkTheme)
    }
}
