package com.github.kiolk.devto.domain.usecases

import com.github.kiolk.devto.utils.theme.ThemeHelper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface GetAppThemeUseCase {

    operator fun invoke(): Flow<Boolean>
}

class GetAppThemeUseCaseImpl(private val themeHelper: ThemeHelper) : GetAppThemeUseCase {

    override fun invoke(): Flow<Boolean> {
        return flow {
            emit(themeHelper.isDarkTheme())
        }
    }
}
