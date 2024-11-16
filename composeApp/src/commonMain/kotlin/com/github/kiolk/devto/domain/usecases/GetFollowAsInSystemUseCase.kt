package com.github.kiolk.devto.domain.usecases

import com.github.kiolk.devto.data.repositories.settings.SettingsRepository
import kotlinx.coroutines.flow.Flow

interface GetFollowAsInSystemUseCase {
    suspend operator fun invoke(): Flow<Boolean>
}

class GetFollowAsInSystemUseCaseImpl(private val settingsRepository: SettingsRepository) : GetFollowAsInSystemUseCase {
    override suspend fun invoke(): Flow<Boolean> {
        return settingsRepository.getFollowAsInSystem()
    }
}
