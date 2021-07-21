package com.manavtamboli.axion.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Lifecycle.State.CREATED
import androidx.lifecycle.Lifecycle.State.STARTED
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

interface FlowScope {

    fun <T> collectFlow(flow : Flow<T>, collector : suspend (T) -> Unit)

    companion object {
        fun LifecycleOwner.flows(
            context : CoroutineContext = EmptyCoroutineContext,
            flowsState : Lifecycle.State = STARTED,
            block : suspend FlowScope.() -> Unit
        ) {
            lifecycleScope.launch {
                repeatOnLifecycle(CREATED){
                    object : FlowScope {
                        override fun <T> collectFlow(flow: Flow<T>, collector: suspend (T) -> Unit) {
                            lifecycleScope.launch(context) {
                                repeatOnLifecycle(flowsState){
                                    flow.collect(collector)
                                }
                            }
                        }
                    }.apply { block() }
                }
            }
        }

        fun LifecycleOwner.flows(block : suspend FlowScope.() -> Unit) = flows(EmptyCoroutineContext, STARTED, block)
    }
}