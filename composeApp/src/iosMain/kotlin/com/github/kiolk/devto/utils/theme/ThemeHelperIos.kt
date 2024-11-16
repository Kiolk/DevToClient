package com.github.kiolk.devto.utils.theme

import platform.UIKit.UITraitCollection
import platform.UIKit.UIUserInterfaceStyle
import platform.UIKit.currentTraitCollection

class ThemeHelperIos : ThemeHelper {
    override fun isDarkTheme(): Boolean {
        val darkTheme = UIUserInterfaceStyle.UIUserInterfaceStyleDark
        return UITraitCollection.currentTraitCollection.userInterfaceStyle == darkTheme
    }
}
