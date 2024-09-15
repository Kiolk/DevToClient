package com.github.kiolk.devto.utils

import android.content.Context

open class AndroidStringProvider(private val context: Context) : StringProvider {

    override fun getString(key: String): String {
        val resId = getId(key)

        if (resId == 0) {
            return key
        }

        return context.getString(resId)
    }

    override fun getQualityString(key: String, itemCount: Int): String {
        val resId = getId(key)

        if (resId == 0) {
            return key
        }
        return context.resources.getQuantityString(resId, itemCount, itemCount);
    }

    private fun getId(key: String): Int {
        return context.resources.getIdentifier(key, null, context.packageName)
    }
}
