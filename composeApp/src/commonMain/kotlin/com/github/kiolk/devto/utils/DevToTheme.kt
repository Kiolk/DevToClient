package com.github.kiolk.devto.utils

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun DevToTheme(content: @Composable () -> Unit) {
    val typography = Typography(
        h1 = TextStyle(fontSize = 14.sp)
    )
    MaterialTheme(typography = typography) {
        content()
    }
}
