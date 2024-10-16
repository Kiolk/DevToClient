package com.github.kiolk.devto.presentation.screens.search.model

import com.github.kiolk.devto.data.repositories.datasources.network.models.SortingType
import com.github.kiolk.devto.utils.localisation.StringsKeys

sealed class SortingTypeUi(override val key: String) : SortTypeUi(key) {
    data object Related : SortingTypeUi(StringsKeys.RELATED)
    data object Latest : SortingTypeUi(StringsKeys.LATEST)
    sealed class Top(key: String) : SortingTypeUi(key) {
        data object Week : Top(StringsKeys.TOP_WEEK)
        data object Month : Top(StringsKeys.TOP_MONTH)
        data object Year : Top(StringsKeys.TOP_YEAR)
        data object Infinity : Top(StringsKeys.TOP_INFINITY)
    }

    fun mapToSortingType(): SortingType {
        return when (this) {
            Latest -> SortingType.Latest
            Related -> SortingType.Relevant
            Top.Infinity -> SortingType.Infinity
            Top.Month -> SortingType.Month
            Top.Week -> SortingType.Week
            Top.Year -> SortingType.Year
        }
    }
}
