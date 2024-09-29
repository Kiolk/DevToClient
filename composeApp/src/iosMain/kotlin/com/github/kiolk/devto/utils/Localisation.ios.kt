package com.github.kiolk.devto.utils

import com.github.kiolk.devto.utils.localisation.StringProvider
import platform.Foundation.NSBundle
import platform.Foundation.NSString
import platform.Foundation.NSURL
import platform.Foundation.localizedStringWithFormat
import platform.Foundation.stringWithFormat

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

    override fun getString(key: String, value: Int): String {
        val format = getString(key)
        return NSString.stringWithFormat(format, value)
    }

    override fun getFormattedString(key: String, vararg arguments: Any?): String {
        val format = getString(key)
        val a = arguments
        return when (arguments.size) {
            0 -> NSString.stringWithFormat(format)
            1 -> NSString.stringWithFormat(format, a[0])
            2 -> NSString.stringWithFormat(format, a[0], a[1])
            3 -> NSString.stringWithFormat(format, a[0], a[1], a[2])
            4 -> NSString.stringWithFormat(format, a[0], a[1], a[2], a[3])
            5 -> NSString.stringWithFormat(format, a[0], a[1], a[2], a[3], a[4])
            6 -> NSString.stringWithFormat(format, a[0], a[1], a[2], a[3], a[4], a[5])
            7 -> NSString.stringWithFormat(format, a[0], a[1], a[2], a[3], a[4], a[5], a[6])
            8 -> NSString.stringWithFormat(format, a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7])
            9 -> NSString.stringWithFormat(format, a[0], a[1], a[2], a[3], a[4], a[5], a[6], a[7], a[8])
            else -> error("Too many arguments.")
        }
    }

    override fun getQualityString(key: String, itemCount: Int): String {
        val format = getString(key)

        return NSString.localizedStringWithFormat(format, itemCount)
    }
}
