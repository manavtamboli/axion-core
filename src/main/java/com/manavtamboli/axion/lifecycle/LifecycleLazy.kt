package com.manavtamboli.axion.lifecycle

import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.*
import androidx.lifecycle.Lifecycle.State

fun <T> Lazy<T>.initializeOn(owner: LifecycleOwner, initState: State) : Lazy<T> = AutoLifecycleLazy(owner, initState){ value }

fun <T> Fragment.viewLifecycleLazy(initializer : () -> T) : Lazy<T> = object : LifecycleLazy<T>(initializer) {
    override val owner: LifecycleOwner
        get() = viewLifecycleOwner
}

fun <T> Fragment.lifecycleLazy(initializer: () -> T): Lazy<T> = object : LifecycleLazy<T>(initializer){
    override val owner: LifecycleOwner
        get() = this@lifecycleLazy
}

fun <T> Fragment.lifecycleLazy(initState: State, initializer: () -> T) : Lazy<T> = AutoLifecycleLazy(this, initState, initializer)


fun <T> ComponentActivity.lifecycleLazy(initializer: () -> T) : Lazy<T> = object : LifecycleLazy<T>(initializer) {
    override val owner: LifecycleOwner
        get() = this@lifecycleLazy
}

fun <T> ComponentActivity.lifecycleLazy(initState: State, initializer: () -> T) : Lazy<T> = AutoLifecycleLazy(this, initState, initializer)

abstract class LifecycleLazy<T> (protected val initializer: () -> T): Lazy<T>, LifecycleObserver {

    abstract val owner : LifecycleOwner

    protected object Empty
    protected var _value : Any? = Empty

    protected open fun initialize(){
        if (!isInitialized()) {
            _value = initializer()
            owner.lifecycle.addObserver(this)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    protected fun onDestroy(){
        _value = Empty
    }

    @Suppress("Unchecked_Cast")
    final override val value: T
        get() {
            initialize()
            return _value as T
        }

    final override fun isInitialized() = _value != Empty
}

private class AutoLifecycleLazy<T>(override val owner: LifecycleOwner, private val initState : State, initializer: () -> T) : LifecycleLazy<T>(initializer) {

    init {
        owner.lifecycle.addObserver(this)
    }

    override fun initialize() {
        if (!isInitialized())
            _value = initializer()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    private fun onAny(){
        if (owner.lifecycle.currentState == initState && !isInitialized()){
            initialize()
        }
    }
}