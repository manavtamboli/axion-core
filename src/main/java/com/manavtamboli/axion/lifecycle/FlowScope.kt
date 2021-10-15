package com.manavtamboli.axion.lifecycle

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.lifecycle.Lifecycle.State.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

interface FlowScope {
    fun <T> collectFlow(flow : Flow<T>, collector : suspend (T) -> Unit)
}

fun Fragment.flows(block: suspend FlowScope.() -> Unit) = flows(this, block)

fun ComponentActivity.flows(block: suspend FlowScope.() -> Unit) = flows(this, block)

private fun flows(owner : LifecycleOwner, block : suspend FlowScope.() -> Unit){
    owner.run {
        lifecycleScope.launch {
            whenCreated {
                object : FlowScope {
                    override fun <T> collectFlow(flow: Flow<T>, collector: suspend (T) -> Unit) {
                        this@whenCreated.launch {
                            repeatOnLifecycle(STARTED){
                                flow.collect(collector)
                            }
                        }
                    }
                }.apply { block() }
            }
        }
    }
}