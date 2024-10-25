package com.github.kiolk.devto.data.repositories.datasources.network.mappers

import com.github.kiolk.devto.data.repositories.datasources.network.models.SortingType
import com.github.kiolk.devto.domain.models.SortBy
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlin.time.DurationUnit
import kotlin.time.toDuration

private const val YEARS_AGO_FOR_INFINITY = 5

fun SortingType.toSortBy(): String {
    return when (this) {
        SortingType.Infinity -> SortBy.PUBLIC_REACTION_COUNT.value
        SortingType.Latest -> SortBy.PUBLISHED_AT.value
        SortingType.Month -> SortBy.PUBLIC_REACTION_COUNT.value
        SortingType.Relevant -> SortBy.HOTNESS_SCORE.value
        SortingType.Week -> SortBy.PUBLIC_REACTION_COUNT.value
        SortingType.Year -> SortBy.PUBLIC_REACTION_COUNT.value
    }
}

fun SortingType.toTime(): Instant? {
    val currentInstant: Instant = Clock.System.now()

    return when (this) {
        SortingType.Infinity -> currentInstant.minus(365.toDuration(DurationUnit.DAYS) * YEARS_AGO_FOR_INFINITY)
        SortingType.Month -> currentInstant.minus(30.toDuration(DurationUnit.DAYS))
        SortingType.Week -> currentInstant.minus(7.toDuration(DurationUnit.DAYS))
        SortingType.Year -> currentInstant.minus(365.toDuration(DurationUnit.DAYS))
        else -> null
    }
}
