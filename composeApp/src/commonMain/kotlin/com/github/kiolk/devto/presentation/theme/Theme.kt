package com.github.kiolk.devto.presentation.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.github.kiolk.devto.utils.colors.DevToColors

val darkColorPalette = darkColors(
    primary = DevToColors.yellow,
    primaryVariant = DevToColors.paleWhite,
    secondary = DevToColors.light_blue,
    surface = DevToColors.raisinBlack,
)

val lightColorPalette = lightColors(
    primary = DevToColors.yellow,
    primaryVariant = DevToColors.black,
    secondary = DevToColors.light_blue,
    surface = DevToColors.white,
)


@Composable
fun DevToTheme(
    isDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if(isDarkTheme) {
        darkColorPalette
    } else {
        lightColorPalette
    }


    MaterialTheme(
        colors = colors,
        content = content
    )
}
