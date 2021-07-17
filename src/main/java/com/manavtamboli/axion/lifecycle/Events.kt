package com.manavtamboli.axion.lifecycle

import androidx.lifecycle.*

inline fun LifecycleOwner.doOnStart(crossinline block : () -> Unit) : LifecycleObserver {
    val observer = object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_START)
        private fun doOnStart() = block()
    }
    lifecycleScope.launchWhenCreated { lifecycle.addObserver(observer) }
    return observer
}

inline fun LifecycleOwner.doOnResume(crossinline block : () -> Unit) : LifecycleObserver {
    val observer = object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
        private fun doOnStart() = block()
    }
    lifecycleScope.launchWhenCreated { lifecycle.addObserver(observer) }
    return observer
}

inline fun LifecycleOwner.doOnStop(crossinline block : () -> Unit) : LifecycleObserver {
    val observer = object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
        private fun doOnStart() = block()
    }
    lifecycleScope.launchWhenCreated { lifecycle.addObserver(observer) }
    return observer
}

inline fun LifecycleOwner.doOnPause(crossinline block : () -> Unit) : LifecycleObserver {
    val observer = object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        private fun doOnStart() = block()
    }
    lifecycleScope.launchWhenCreated { lifecycle.addObserver(observer) }
    return observer
}

inline fun LifecycleOwner.doOnCreate(crossinline block : () -> Unit) : LifecycleObserver {
    val observer = object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        private fun doOnStart() = block()
    }
    lifecycleScope.launchWhenCreated { lifecycle.addObserver(observer) }
    return observer
}

inline fun LifecycleOwner.doOnDestroy(crossinline block : () -> Unit) : LifecycleObserver {
    val observer = object : LifecycleObserver {
        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        private fun doOnStart() = block()
    }
    lifecycleScope.launchWhenCreated { lifecycle.addObserver(observer) }
    return observer
}