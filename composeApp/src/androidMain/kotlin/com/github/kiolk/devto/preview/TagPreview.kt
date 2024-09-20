package com.github.kiolk.devto.preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.github.kiolk.devto.presentation.screens.home.models.TagUi
import com.github.kiolk.devto.presentation.theme.DevToTheme
import com.github.kiolk.devto.presentation.views.tag.Tag

@Preview(showBackground = true, device = Devices.DEFAULT)
@Composable
fun TagPreview(@PreviewParameter(TagProvider::class) tag: TagUi) {
    DevToTheme() {
        Tag(tag) {}
    }
}

class TagProvider : PreviewParameterProvider<TagUi> {
    override val values: Sequence<TagUi> = sequenceOf(
        TagUi("Android"),
        TagUi("iOS", isFlare = true, textColor = Color.Red, backgroundColor = Color.Red),
        TagUi("ReactNative", isFlare = false, textColor = Color.Blue, backgroundColor = Color.Green)
    )
}
