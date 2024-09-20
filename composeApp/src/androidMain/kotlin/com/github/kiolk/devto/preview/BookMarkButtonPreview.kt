package com.github.kiolk.devto.preview

import androidx.compose.runtime.Composable
import com.github.kiolk.devto.presentation.screens.home.mappers.mapToArticleUi
import com.github.kiolk.devto.presentation.views.buttons.bookMark.BookMarkButton
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun BookMarkButtonPreview2() {
    BookMarkButton(article = fakeArticle.mapToArticleUi(MockStringProvider))
}
