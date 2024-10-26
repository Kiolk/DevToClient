package com.github.kiolk.devto.presentation.screens.feed.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.github.kiolk.devto.presentation.screens.search.model.TagSearchUi
import com.github.kiolk.devto.utils.colors.DevToColors

@Composable
fun TagHeader(tag: TagSearchUi) {
    Column(modifier = Modifier.background(color = MaterialTheme.colors.surface)) {
        Box(modifier = Modifier.fillMaxWidth().height(20.dp).background(tag.backgroundColor))
        Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = tag.tag.badgeImageUrl, // replace with working URL
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .aspectRatio(1f)
            )
            Column(modifier = Modifier.padding(start = 10.dp, top = 10.dp, bottom = 10.dp)) {
                Row {
                    Text(
                        "#",
                        fontWeight = FontWeight.Bold,
                        color = DevToColors.gray
                    )
                    Text(
                        tag.tag.name,
                        fontWeight = FontWeight.Bold
                    )
                }
                tag.summary?.let {
                    Text(it, modifier = Modifier.fillMaxWidth())
                }
            }
        }
    }
}
