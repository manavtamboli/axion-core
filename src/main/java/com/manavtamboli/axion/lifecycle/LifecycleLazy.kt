package com.manavtamboli.axion.lifecycle

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

class LifecycleLazy<out T> private constructor(
    private val owner: LifecycleOwner,
    private val initializer : () -> T,
    private val initializeOn : Initialization
) : Lazy<T>, LifecycleObserver {

    private object Empty
    private val lifecycle get() = owner.lifecycle

    private var _value : Any? = Empty

    private fun initialize(){
        if (!isInitialized()) {
            _value = initializer()
            lifecycle.addObserver(this)
        }
    }

    override fun isInitialized() = _value != Empty

    @Suppress("UNCHECKED_CAST")
    override val value: T
        get(){
            initialize()
            return _value as T
        }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onStart(){
        if (initializeOn == Initialization.OnStart) initialize()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy(){
        _value = Empty
    }

    enum class Initialization { Lazily, OnStart }

    companion object {
        fun <T> LifecycleOwner.lifecycleLazy(initializer : () -> T) : Lazy<T> = LifecycleLazy(this, initializer, Initialization.Lazily)
        fun <T> LifecycleOwner.lifecycleLazy(initializeOn: Initialization, initializer : () -> T) : Lazy<T> =
            LifecycleLazy(this, initializer, initializeOn)
    }
}