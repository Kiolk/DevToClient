package com.github.kiolk.devto.utils

import android.content.Context

open class AndroidStringProvider(private val context: Context) : StringProvider {

    override fun getString(key: String): String {
        val resId = context.resources.getIdentifier(key, "string", context.packageName)

        if (resId == 0) {
            return key
        }

        return context.getString(resId)
    }
}
