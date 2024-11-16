package com.github.kiolk.devto

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.navigator.Navigator
import com.github.kiolk.devto.domain.usecases.GetAppThemeUseCase
import com.github.kiolk.devto.domain.usecases.SystemThemeChangedUseCase
import com.github.kiolk.devto.presentation.screens.main.MainScreen
import com.github.kiolk.devto.presentation.theme.DevToTheme
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import platform.UIKit.UITraitCollection
import platform.UIKit.UIUserInterfaceStyle
import platform.UIKit.UIViewController
import platform.UIKit.addChildViewController
import platform.UIKit.didMoveToParentViewController

class MainViewController : KoinComponent {
    private val getAppThemeUseCase: GetAppThemeUseCase by inject()
    private val systemThemeChangedUseCase: SystemThemeChangedUseCase by inject()

    fun create(): UIViewController {
        val themeController = ThemeAwareViewController().apply {
            onSystemThemeChanged = {
                systemThemeChangedUseCase(it)
            }
        }

        val controller = ComposeUIViewController {
            val isDark by getAppThemeUseCase().collectAsState(false)
            DevToTheme(isDarkTheme = isDark) {
                Navigator(MainScreen())
            }
        }

        controller.addChildViewController(themeController)
        controller.view.addSubview(themeController.view)
        themeController.didMoveToParentViewController(controller)

        return controller
    }
}

class ThemeAwareViewController : UIViewController(null, null) {
    var onSystemThemeChanged: ((Boolean) -> Unit)? = null

    override fun traitCollectionDidChange(previousTraitCollection: UITraitCollection?) {
        super.traitCollectionDidChange(previousTraitCollection)
        if (traitCollection.userInterfaceStyle != previousTraitCollection?.userInterfaceStyle) {
            val isDarkMode =
                traitCollection.userInterfaceStyle == UIUserInterfaceStyle.UIUserInterfaceStyleDark
            onSystemThemeChanged?.invoke(isDarkMode)
        }
    }
}
