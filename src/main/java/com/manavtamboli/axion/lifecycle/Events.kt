package com.manavtamboli.axion.lifecycle

import androidx.lifecycle.*
import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.Lifecycle.State.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

fun LifecycleOwner.onCreate(block: suspend CoroutineScope.() -> Unit) = onState(CREATED, block)

fun LifecycleOwner.onStart(block: suspend CoroutineScope.() -> Unit) = onState(STARTED, block)

fun LifecycleOwner.onResume(block: suspend CoroutineScope.() -> Unit) = onState(RESUMED, block)

fun LifecycleOwner.doWhenCreated(block: suspend CoroutineScope.() -> Unit) = doWhenState(CREATED, block)

fun LifecycleOwner.doWhenStarted(block: suspend CoroutineScope.() -> Unit) = doWhenState(STARTED, block)

fun LifecycleOwner.doWhenResumed(block: suspend CoroutineScope.() -> Unit) = doWhenState(RESUMED, block)


internal fun LifecycleOwner.onState(state : State, block: suspend CoroutineScope.() -> Unit){
    lifecycleScope.launch {
        lifecycle.whenStateAtLeast(INITIALIZED){
            repeatOnLifecycle(state, block)
        }
    }
}

internal fun LifecycleOwner.doWhenState(state: State, block: suspend CoroutineScope.() -> Unit){
    lifecycleScope.launch {
        lifecycle.whenStateAtLeast(state, block)
    }
}