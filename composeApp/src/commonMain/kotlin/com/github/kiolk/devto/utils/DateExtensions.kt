package com.github.kiolk.devto.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

fun Instant.toPublicationDate(): String {
    return PUBLICATION_DATE_FORMAT.format(this.toLocalDateTime(TimeZone.currentSystemDefault()))
}

fun Instant.toPublicationDateAgo(stringProvider: StringProvider): String {
    val currentTime = Clock.System.now()
    val difference = currentTime - this
    val inDays = difference.inWholeDays

    if (inDays >= 31) {
        return ""
    } else if (inDays >= 1) {
        //TODO localisation
        return "($inDays ${
            stringProvider.getQualityString(
                "days_ago_since_publication",
                inDays.toInt()
            )
        })"
    }

    val inHours = difference.inWholeHours

    if (inHours >= 24) {
        //TODO localisation
        return "($inHours hours)"
    }

    val inMinutes = difference.inWholeMinutes

    if (inMinutes >= 1) {
        return "($inMinutes minutes)"
    }

    return ""
}

val PUBLICATION_DATE_FORMAT: DateTimeFormat<LocalDateTime> = LocalDateTime.Format {
    monthName(names = MonthNames.ENGLISH_ABBREVIATED)
    char(' ')
    dayOfMonth()
}
