package com.github.kiolk.devto.presentation.screens.search.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.github.kiolk.devto.presentation.screens.search.model.UserSearchUi
import com.github.kiolk.devto.presentation.screens.user.UserScreen
import com.github.kiolk.devto.presentation.views.article.UserNameWithOrganisation
import com.github.kiolk.devto.presentation.views.avatar.UserOrganisationAvatar

@Composable
fun UserSearchCard(user: UserSearchUi) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = 2.dp,
        border = BorderStroke(0.5.dp, Color.LightGray),
    ) {
        val navigator = LocalNavigator.currentOrThrow
        Column {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.Top) {
                UserOrganisationAvatar(
                    user.user,
                    organization = null,
                    onUserClick = { navigator.push(UserScreen(it)) }
                )
                Column(
                    modifier = Modifier.padding(start = 2.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Row {
                        Column(verticalArrangement = Arrangement.Top) {
                            UserNameWithOrganisation(
                                user.user,
                                organization = null,
                                onUserClick = { navigator.push(UserScreen(it)) },
                            )
                            Text(user.user.username)
                        }
                    }
                }
            }
        }
    }
}
