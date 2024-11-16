import UIKit
import ComposeApp

class IosThemeHelper: ThemeHelper {
    func isDarkTheme() -> Bool {
        if #available(iOS 13.0, *) {
            return UITraitCollection.current.userInterfaceStyle == .dark
        } else {
            return false
        }
    }
}
