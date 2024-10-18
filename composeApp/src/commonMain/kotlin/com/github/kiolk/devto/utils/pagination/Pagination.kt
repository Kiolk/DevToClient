package com.github.kiolk.devto.utils.pagination

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class Pagination<T>(
    private val source: suspend (page: Int) -> List<T>,
    private val onNewPortionLoaded: (data: List<T>) -> Unit,
    private val scope: CoroutineScope,
    private val portionSize: Int = 10,
    startPosition: Int = START_LOADING_POSITION,
) {

    private var startPage: Int = startPosition
    private var job: Job? = null
    private var isReachedEnd = false

    fun startLoading() {
        loadNewPortion()
    }

    fun loadNewPortion() {
        if (job != null || isReachedEnd) {
            return
        }

        job = scope.launch(Dispatchers.IO) {
            startPage += 1

            val newPortion = source(startPage)

            launch(Dispatchers.Main) {
                isReachedEnd = newPortion.isEmpty() || newPortion.size < portionSize
                onNewPortionLoaded(newPortion)
                job = null
            }
        }
    }

    fun restart() {
        job?.cancel()
        job = null
        isReachedEnd = false
        startPage = START_LOADING_POSITION
        startLoading()
    }

    companion object {
        const val START_LOADING_POSITION = -1
    }
}
