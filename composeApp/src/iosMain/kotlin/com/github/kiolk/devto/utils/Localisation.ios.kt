package com.github.kiolk.devto.utils

import com.github.kiolk.devto.utils.localisation.StringProvider
import platform.Foundation.NSBundle
import platform.Foundation.NSString
import platform.Foundation.NSURL
import platform.Foundation.localizedStringWithFormat

class IosStringProvider : StringProvider {
    override fun getString(key: String): String {

        val localizedString = NSBundle.mainBundle.localizedStringForKey(key, key, null)

        if (localizedString != key) {
            return localizedString
        }

        val baseResourcePath = NSBundle.mainBundle.pathForResource("Base", "lproj")
            ?.let { NSURL(fileURLWithPath = it) }
        val baseBundle = baseResourcePath?.let { NSBundle(it) }

        if (baseBundle != null) {
            val baseString = baseBundle.localizedStringForKey(key, key, null)

            return baseString
        }

        return key
    }

    override fun getQualityString(key: String, itemCount: Int): String {
        val format = getString(key)

        return NSString.localizedStringWithFormat(format, itemCount)
    }
}
