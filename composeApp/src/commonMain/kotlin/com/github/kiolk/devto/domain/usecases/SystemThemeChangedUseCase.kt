package com.github.kiolk.devto.domain.usecases

import com.github.kiolk.devto.data.repositories.settings.SettingsRepository

interface SystemThemeChangedUseCase {

    operator fun invoke(isDarkTheme: Boolean)
}

class SystemThemeChangedUseCaseImpl(private val settingsRepository: SettingsRepository) : SystemThemeChangedUseCase {

    override fun invoke(isDarkTheme: Boolean) {
        settingsRepository.onSystemThemeChanged(isDarkTheme)
    }
}
