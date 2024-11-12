import SwiftUI
import ComposeApp

@main
struct iOSApp: App {

    init() {
        let iosThemeHelper: ThemeHelper = IosThemeHelper()
        KoinHelperKt.doInitKoin(themeHelper: iosThemeHelper)
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
