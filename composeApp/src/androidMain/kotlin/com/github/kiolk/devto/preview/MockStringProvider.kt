package com.github.kiolk.devto.preview

import com.github.kiolk.devto.utils.localisation.StringProvider

object MockStringProvider : StringProvider {
    override fun getString(key: String): String {
        return "days"
    }

    override fun getQualityString(key: String, itemCount: Int): String {
        return "days"
    }
}
