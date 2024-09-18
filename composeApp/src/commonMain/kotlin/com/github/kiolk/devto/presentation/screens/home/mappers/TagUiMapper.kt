package com.github.kiolk.devto.presentation.screens.home.mappers

import com.github.kiolk.devto.presentation.models.FlareTag
import com.github.kiolk.devto.presentation.screens.home.models.TagUi
import com.github.kiolk.devto.utils.colors.hexToColor

fun String.toTagUi(flareTag: FlareTag?): TagUi {
    if (flareTag != null && flareTag.name == this) {
        val tag = TagUi(
            name = this,
            backgroundColor = hexToColor(flareTag.bgColorHex),
            textColor = hexToColor(flareTag.textColorHex),
            isFlare = true,
        )

        return tag
    }

    return TagUi(name = this)
}
