package com.manavtamboli.axion.lifecycle

import androidx.lifecycle.*
import androidx.lifecycle.Lifecycle.State.STARTED
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
                whenStarted {
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