package com.github.kiolk.devto.presentation.screens.search.model

import com.github.kiolk.devto.domain.models.Organization

data class OrganizationSearchUi(
    val organization: Organization,
) : SearchableUi
