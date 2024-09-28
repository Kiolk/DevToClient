package com.github.kiolk.devto.presentation.screens.user

import cafe.adriel.voyager.core.model.ScreenModel
import com.github.kiolk.devto.presentation.models.User
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class UserScreenModel(userName: String) : ScreenModel {

    private val _user = MutableStateFlow(User(name = "", username = userName))
    val user: StateFlow<User> = _user

}
