package com.manavtamboli.axion.kotlin

import kotlinx.coroutines.flow.*

fun SharingStarted.Companion.MinSubscriptions(minSubscriptions: Int) = SharingStarted { subscriptionCount ->
    require(minSubscriptions > 0)
    subscriptionCount
        .transformLatest { count ->
            if (count >= minSubscriptions) emit(SharingCommand.START)
            else emit(SharingCommand.STOP_AND_RESET_REPLAY_CACHE)
        }
        .dropWhile { it != SharingCommand.START }
        .distinctUntilChanged()
}