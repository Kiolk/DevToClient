package com.github.kiolk.devto

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

// Injection Boostrap Helper
class GreetingHelper : KoinComponent {
    private val platform: Platform by inject()
    fun greet(): String = platform.name
}

class Greeting {

    fun greet(): String {
        return "Hello, ${GreetingHelper().greet()}!"
    }
}
