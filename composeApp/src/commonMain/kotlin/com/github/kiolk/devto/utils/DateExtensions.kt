package com.github.kiolk.devto.utils

import com.github.kiolk.devto.utils.localisation.StringProvider
import com.github.kiolk.devto.utils.localisation.StringsKeys.DAYS_AGO_SINCE_PUBLICATION
import com.github.kiolk.devto.utils.localisation.StringsKeys.HOURS_AGO_SINCE_PUBLICATION
import com.github.kiolk.devto.utils.localisation.StringsKeys.MINUTES_AGO_SINCE_PUBLICATION
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

private const val DAYS_IN_MONTH = 31

fun Instant.toPublicationDateAgo(stringProvider: StringProvider): String {
    val currentTime = Clock.System.now()
    val difference = currentTime - this
    val inDays = difference.inWholeDays

    if (inDays >= DAYS_IN_MONTH) {
        return ""
    } else if (inDays >= 1) {
        return "($inDays ${
            stringProvider.getQualityString(
                DAYS_AGO_SINCE_PUBLICATION,
                inDays.toInt()
            )
        })"
    }

    val inHours = difference.inWholeHours

    if (inHours >= 1) {
        return "($inHours  ${
            stringProvider.getQualityString(
                HOURS_AGO_SINCE_PUBLICATION,
                inDays.toInt()
            )
        })"
    }

    val inMinutes = difference.inWholeMinutes

    if (inMinutes >= 1) {
        return "($inMinutes ${
            stringProvider.getQualityString(
                MINUTES_AGO_SINCE_PUBLICATION,
                inDays.toInt()
            )
        })"
    }

    return ""
}

val PUBLICATION_DATE_FORMAT: DateTimeFormat<LocalDateTime> = LocalDateTime.Format {
    monthName(names = MonthNames.ENGLISH_ABBREVIATED)
    char(' ')
    dayOfMonth()
}
