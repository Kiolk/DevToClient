package com.github.kiolk.devto.preview

import com.github.kiolk.devto.utils.StringProvider

object MockStringProvider : StringProvider {
    override fun getString(key: String): String {
        return "deys"
    }
}
