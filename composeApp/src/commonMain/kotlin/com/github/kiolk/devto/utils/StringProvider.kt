package com.github.kiolk.devto.utils

interface StringProvider {

    fun getString(key: String): String

    fun getQualityString(key: String, itemCount: Int): String
}
