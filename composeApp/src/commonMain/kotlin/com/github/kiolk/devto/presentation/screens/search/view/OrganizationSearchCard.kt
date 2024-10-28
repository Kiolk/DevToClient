package com.github.kiolk.devto.presentation.screens.search.view

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.github.kiolk.devto.presentation.screens.search.model.OrganizationSearchUi

@Composable
fun OrganizationSearchCard(
    organization: OrganizationSearchUi,
    onOrganisationClick: (OrganizationSearchUi) -> Unit = {},
) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable {
            onOrganisationClick(organization)
        },
        shape = RoundedCornerShape(4.dp),
        elevation = 2.dp,
        border = BorderStroke(0.5.dp, Color.LightGray),
    ) {
        Column {
            Row(modifier = Modifier.padding(16.dp), verticalAlignment = Alignment.Top) {
                Box(
                    modifier = Modifier.size(40.dp)
                        .background(Color.White, RoundedCornerShape(4.dp))
                        .border(1.dp, Color.LightGray, RoundedCornerShape(4.dp))
                ) {
                    AsyncImage(
                        model = organization.organization.profileImage,
                        contentDescription = null,
                        modifier = Modifier
                            .clip(RoundedCornerShape(4.dp))
                    )
                }
                Column(
                    modifier = Modifier.padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.Top
                ) {
                    Text(
                        organization.organization.name,
                        style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold)
                    )
                    Text(
                        "@" + organization.organization.slug,
                        style = MaterialTheme.typography.caption
                    )
                    Text(
                        organization.organization.summary,
                        style = MaterialTheme.typography.caption
                    )
                }
            }
        }
    }
}
