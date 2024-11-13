package com.github.kiolk.devto

import platform.UIKit.UIDevice
import platform.UIKit.UITraitCollection
import platform.UIKit.UIUserInterfaceStyle
import platform.UIKit.currentTraitCollection

actual class Platform actual constructor() {
    val a = UITraitCollection.currentTraitCollection.userInterfaceStyle == UIUserInterfaceStyle.UIUserInterfaceStyleDark
    actual val name: String =
        UIDevice.currentDevice.systemName() + " ---- " + UIDevice.currentDevice.systemVersion
}
