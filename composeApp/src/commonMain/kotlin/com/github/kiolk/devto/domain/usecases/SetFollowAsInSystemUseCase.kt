package com.github.kiolk.devto.domain.usecases

import com.github.kiolk.devto.data.repositories.settings.SettingsRepository

interface SetFollowAsInSystemUseCase {
    suspend operator fun invoke(isFollowAsInSystem: Boolean)
}

class SetFollowAsInSystemUseCaseImpl(private val settingsRepository: SettingsRepository) : SetFollowAsInSystemUseCase {
    override suspend fun invoke(isFollowAsInSystem: Boolean) {
        settingsRepository.setFollowAsInSystem(isFollowAsInSystem)
    }
}
