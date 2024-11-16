package com.github.kiolk.devto.presentation.screens.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Checkbox
import androidx.compose.material.Divider
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.core.screen.ScreenKey
import cafe.adriel.voyager.core.screen.uniqueScreenKey
import cafe.adriel.voyager.koin.koinScreenModel
import com.github.kiolk.devto.utils.localisation.StringProvider
import com.github.kiolk.devto.utils.localisation.StringsKeys
import org.koin.mp.KoinPlatform.getKoin

class ProfileScreen : Screen {

    override val key: ScreenKey = uniqueScreenKey
    private val stringProvider = getKoin().get<StringProvider>()

    @Composable
    override fun Content() {
        val screenModel = koinScreenModel<ProfileScreenModel>()
        val isDarkTheme by screenModel.isDarkTheme.collectAsState()
        val isFollowAsInSystem by screenModel.isFollowAsInSystem.collectAsState()

        Column(modifier = Modifier.fillMaxSize().padding(8.dp)) {
            Text(stringProvider.getString(StringsKeys.SETTINGS))
            Divider(modifier = Modifier.padding(vertical = 4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(stringProvider.getString(StringsKeys.DARK_THEME))
                Switch(
                    enabled = !isFollowAsInSystem,
                    checked = isDarkTheme,
                    onCheckedChange = { screenModel.onDarkThemeChanged(it) }
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(stringProvider.getString(StringsKeys.FOLLOW_AS_IN_SYSTEM))
                Checkbox(
                    checked = isFollowAsInSystem,
                    onCheckedChange = { screenModel.onFollowAsInSystemChecked(it) }
                )
            }
        }
    }
}
