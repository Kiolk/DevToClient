package com.github.kiolk.devto.utils.pagination

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch

class Pagination<T>(
    private val source: suspend (page: Int) -> List<T>,
    private val onNewPortionLoaded: (data: List<T>) -> Unit,
    private val scope: CoroutineScope,
    startPosition: Int = -1,
) {

    private var startPage: Int = startPosition

    fun startLoading() {
        loadNewPortion()
    }

    fun loadNewPortion() {
        scope.launch(Dispatchers.IO) {
            startPage += 1

            val newPortion = source(startPage)

            launch(Dispatchers.Main) {
                onNewPortionLoaded(newPortion)
            }
        }
    }
}
