package com.manavtamboli.axion.kotlin

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

fun <T> Flow<T>.launchInIO(scope : CoroutineScope): Job = scope.launch(Dispatchers.IO){
    collect()
}