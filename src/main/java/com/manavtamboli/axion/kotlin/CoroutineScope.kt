package com.manavtamboli.axion.kotlin

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

inline fun CoroutineScope.launchInIO(crossinline block: suspend CoroutineScope.() -> Unit) = launch(Dispatchers.IO) { block() }

inline fun CoroutineScope.launchInMain(crossinline block: suspend CoroutineScope.() -> Unit) = launch(Dispatchers.Main) { block() }