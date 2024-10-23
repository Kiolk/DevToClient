package com.github.kiolk.devto.presentation.screens.user

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import com.github.kiolk.devto.presentation.feed.StubWebScreen
import org.koin.core.parameter.parametersOf

class UserScreen(private val userName: String) : Screen {

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<UserScreenModel>(parameters = { parametersOf(userName) })

        val user by screenModel.user.collectAsState()

        StubWebScreen("https://dev.to/${user.username}")
    }
}
