package com.github.kiolk.devto.presentation.screens.search.view

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.github.kiolk.devto.presentation.screens.search.model.OrganizationSearchUi

@Composable
fun OrganizationSearchCard(organization: OrganizationSearchUi) {
    Text(organization.organization.name)
}
