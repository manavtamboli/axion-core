package com.manavtamboli.axion.kotlin

import kotlinx.coroutines.Deferred

suspend fun <T> Deferred<T>.awaitCatching() : Result<T> {
    return runCatching { await() }
}