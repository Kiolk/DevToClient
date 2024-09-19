package com.github.kiolk.devto.utils.colors

import androidx.compose.ui.graphics.Color

actual fun hexToColor(hex: String): Color {
    return try {
        Color(hex.replace("#", "FF").toLong(16))
    } catch (e: Exception) {
        return Color.White
    }
}
