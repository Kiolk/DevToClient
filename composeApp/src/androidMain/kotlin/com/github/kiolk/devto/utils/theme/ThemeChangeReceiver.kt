package com.github.kiolk.devto.utils.theme

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import com.github.kiolk.devto.domain.usecases.SystemThemeChangedUseCase

class ThemeChangeReceiver(private val themeChangedUseCase: SystemThemeChangedUseCase) :
    BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_CONFIGURATION_CHANGED && context != null) {
            val currentNightMode =
                context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK
            val isDark = currentNightMode == Configuration.UI_MODE_NIGHT_NO
            themeChangedUseCase(isDark)
        }
    }

    fun register(context: Context) {
        val filter = IntentFilter(Intent.ACTION_CONFIGURATION_CHANGED)
        context.registerReceiver(this, filter)
    }

    fun unregister(context: Context) {
        context.unregisterReceiver(this)
    }
}
