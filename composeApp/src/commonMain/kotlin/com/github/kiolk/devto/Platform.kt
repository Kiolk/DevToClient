package com.github.kiolk.devto

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform