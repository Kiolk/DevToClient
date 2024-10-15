package com.github.kiolk.devto.domain.models

data class Organization(
    val name: String,
    val username: String,
    val slug: String,
    val profileImage: String,
    val profileImage90: String,
) : Searchable
