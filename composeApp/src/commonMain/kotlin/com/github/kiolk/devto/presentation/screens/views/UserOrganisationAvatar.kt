package com.github.kiolk.devto.presentation.screens.views

import Organization
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.github.kiolk.devto.presentation.models.User

@Composable
fun UserOrganisationAvatar(user: User, organization: Organization?) {
    if (organization == null) {
        Box(modifier = Modifier.size(35.dp)) {
            Box(
                modifier = Modifier.size(30.dp).background(Color.LightGray, CircleShape)
                    .border(1.dp, Color.LightGray, CircleShape).align(Alignment.Center)
            ) {
                AsyncImage(
                    model = user.profileImage, // replace with working URL
                    contentDescription = null,
                    modifier = Modifier
                        .size(29.dp)
                        .align(Alignment.Center)
                        .clip(CircleShape)
                )
            }
        }
    } else {
        Box(modifier = Modifier.size(35.dp)) {
            Box(
                modifier = Modifier.size(30.dp).background(Color.White, RoundedCornerShape(4.dp))
                    .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                    .align(Alignment.TopStart)
            ) {
                AsyncImage(
                    model = organization.profileImage, // replace with working URL
                    contentDescription = null,
                    modifier = Modifier.size(29.dp)
                        .clip(RoundedCornerShape(4.dp))
                )
            }
            Box(
                modifier = Modifier.size(16.dp).background(Color.LightGray, CircleShape)
                    .border(2.dp, Color.White, CircleShape).align(Alignment.BottomEnd)
            ) {
                AsyncImage(
                    model = user.profileImage, // replace with working URL
                    contentDescription = null,
                    modifier = Modifier
                        .size(14.dp)
                        .align(Alignment.Center)
                        .border(1.dp, Color.LightGray, CircleShape)
                        .clip(CircleShape)
                )
            }
        }
    }
}
