package com.github.kiolk.devto.domain.usecases

import com.github.kiolk.devto.data.repositories.settings.SettingsRepository
import com.github.kiolk.devto.utils.theme.ThemeHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow

interface GetAppThemeUseCase {

    operator fun invoke(): Flow<Boolean>
}

class GetAppThemeUseCaseImpl(
    private val themeHelper: ThemeHelper,
    private val settingsRepository: SettingsRepository,
) : GetAppThemeUseCase {

    override fun invoke(): Flow<Boolean> {
        return flow {
            // TODO check the logic to reduce call to themeHelper.isDarkTheme()
            emit(themeHelper.isDarkTheme())
        }
            .combine(settingsRepository.isDarkSystemTheme) { theme, systemTheme ->
                systemTheme ?: theme
            }
            .combine(settingsRepository.isUserAppThemeDark()) { systemTheme, userTheme ->
                userTheme ?: systemTheme
            }
            .combine(settingsRepository.getFollowAsInSystem()) { userTheme, followAsInSystem ->
                if (followAsInSystem) {
                    themeHelper.isDarkTheme()
                } else {
                    userTheme
                }
            }.distinctUntilChanged()
    }
}
