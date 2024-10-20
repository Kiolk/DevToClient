package com.github.kiolk.devto.presentation.screens.search.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.github.kiolk.devto.presentation.screens.search.model.TagSearchUi

@Composable
fun TagSearchCard(tag: TagSearchUi) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        shape = RoundedCornerShape(4.dp),
        elevation = 2.dp,
        border = BorderStroke(0.5.dp, Color.LightGray),
    ) {
        Column(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
            Row {
                Box(
                    Modifier
                        .size(40.dp)
                        .aspectRatio(1f)
                        .background(
                            color = tag.backgroundColor,
                            shape = RoundedCornerShape(corner = CornerSize(4.dp))
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = AnnotatedString("#"),
                        modifier = Modifier.padding(10.dp),
                        fontSize = 20.sp,
                        color = tag.badgeColor,
                    )
                }
                Column(modifier = Modifier.padding(start = 8.dp)) {
                    Text(
                        tag.tag.name,
                        modifier = Modifier.padding(vertical = 10.dp),
                        fontWeight = FontWeight.Bold
                    )

                    tag.summary?.let {
                        Text(it)
                    }
                }
            }
        }
    }
}
