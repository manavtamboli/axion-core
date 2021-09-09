package com.manavtamboli.axion.lifecycle

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.lifecycle.Lifecycle.State
import androidx.lifecycle.Lifecycle.State.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

interface FlowScope {
    fun <T> collectFlow(flow : Flow<T>, collector : suspend (T) -> Unit)
}

fun Fragment.flows(state: State = STARTED, block: suspend FlowScope.() -> Unit) = flows(this, state, block)

fun ComponentActivity.flows(state: State = STARTED, block: suspend FlowScope.() -> Unit) = flows(this, state, block)


private fun flows(owner: LifecycleOwner, state: State = STARTED, block: suspend FlowScope.() -> Unit){
    owner.run {
        doWhenState(CREATED){
            object : FlowScope {
                override fun <T> collectFlow(flow: Flow<T>, collector: suspend (T) -> Unit) {
                    lifecycleScope.launch {
                        repeatOnLifecycle(state){
                            flow.collect(collector)
                        }
                    }
                }
            }.apply {
                block()
            }
        }
    }
}