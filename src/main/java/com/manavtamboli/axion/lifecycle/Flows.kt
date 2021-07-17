package com.manavtamboli.axion.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

inline fun <T> LifecycleOwner.collectFlow(flow : Flow<T>, crossinline collector : suspend (T) -> Unit){
    lifecycleScope.launch {
        repeatOnLifecycle(STARTED){
            flow.collect(collector)
        }
    }
}

inline fun <T> LifecycleOwner.collectFlow(
    flow : Flow<T>,
    context : CoroutineContext = EmptyCoroutineContext,
    lifecycleState : Lifecycle.State = STARTED,
    crossinline collector : suspend (T) -> Unit
){
    lifecycleScope.launch(context){
        repeatOnLifecycle(lifecycleState){
            flow.collect(collector)
        }
    }
}