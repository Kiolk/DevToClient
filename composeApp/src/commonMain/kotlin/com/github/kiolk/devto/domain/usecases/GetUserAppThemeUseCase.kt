package com.github.kiolk.devto.domain.usecases

import com.github.kiolk.devto.data.repositories.settings.SettingsRepository
import kotlinx.coroutines.flow.Flow

interface GetUserAppThemeUseCase {
    suspend operator fun invoke(): Flow<Boolean?>
}

class GetUserAppThemeUseCaseImpl(private val settingsRepository: SettingsRepository) : GetUserAppThemeUseCase {
    override suspend fun invoke(): Flow<Boolean?> {
        return settingsRepository.isUserAppThemeDark()
    }
}
