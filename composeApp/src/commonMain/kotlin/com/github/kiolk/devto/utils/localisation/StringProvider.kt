package com.github.kiolk.devto.utils.localisation

interface StringProvider {

    fun getString(key: String): String

    fun getFormattedString(key: String, vararg arguments: Any?): String

    fun getQualityString(key: String, itemCount: Int): String
}
