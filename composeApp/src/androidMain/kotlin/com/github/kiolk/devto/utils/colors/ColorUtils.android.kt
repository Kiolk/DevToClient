package com.github.kiolk.devto.utils.colors

import androidx.compose.ui.graphics.Color
import android.graphics.Color as AndroidColor

actual fun hexToColor(hex: String): Color {
    return Color(AndroidColor.parseColor(if (hex.contains("#")) hex else ("#$hex")))
}
