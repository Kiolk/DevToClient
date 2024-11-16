package com.github.kiolk.devto.utils.theme

import android.content.Context
import android.content.res.Configuration

class ThemeHelperAndroid(private val context: Context) : ThemeHelper {
    override fun isDarkTheme(): Boolean {
        return when (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_YES -> true
            else -> false
        }
    }
}
