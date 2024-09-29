package com.github.kiolk.devto.utils

import android.content.Context
import com.github.kiolk.devto.utils.localisation.StringProvider

open class AndroidStringProvider(private val context: Context) : StringProvider {

    override fun getString(key: String): String {
        val resId = context.resources.getIdentifier(key, STRINGS_RESOURCE_TYPE, context.packageName)

        if (resId == 0) {
            return key
        }

        return context.getString(resId)
    }

    override fun getString(key: String, value: Int): String {
        return getFormattedString(key, value)
    }

    override fun getFormattedString(key: String, vararg arguments: Any?): String {
        val resId = context.resources.getIdentifier(key, STRINGS_RESOURCE_TYPE, context.packageName)

        if (resId == 0) {
            return key
        }

        return context.getString(resId, *arguments)
    }

    override fun getQualityString(key: String, itemCount: Int): String {
        val resId = context.resources.getIdentifier(key, PLURALS_RESOURCE_TYPE, context.packageName)

        if (resId == 0) {
            return key
        }
        return context.resources.getQuantityString(resId, itemCount, itemCount);
    }

    private companion object {
        const val STRINGS_RESOURCE_TYPE = "string"
        const val PLURALS_RESOURCE_TYPE = "plurals"
    }
}
