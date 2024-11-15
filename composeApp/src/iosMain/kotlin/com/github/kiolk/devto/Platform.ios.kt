package com.github.kiolk.devto

import platform.UIKit.UIDevice

actual class Platform actual constructor() {
    actual val name: String =
        UIDevice.currentDevice.systemName() + " ---- " + UIDevice.currentDevice.systemVersion
}
