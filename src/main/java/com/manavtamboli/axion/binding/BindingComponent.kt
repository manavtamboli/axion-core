package com.manavtamboli.axion.binding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding

/**
 * Interface to generate bindings.
 * */
interface BindingComponent<B : ViewBinding> {

    /**
     * Property to access the generated binding.
     * */
    val binding : B get() = throw NotImplementedError()

    /**
     * Called after binding is generated.
     * Override this to do things like initializing views, etc.
     * */
    fun B.initialize() {  }

    /**
     * Generates the binding.
     * */
    fun generateBinding(inflater: LayoutInflater, viewGroup: ViewGroup?, lifecycleOwner: LifecycleOwner) {
        throw NotImplementedError()
    }
}

internal class BindingComponentImpl <B : ViewBinding>(private val bindingClass: Class<B>) : BindingComponent<B>,
    LifecycleObserver {

    private var _binding: B? = null
    override val binding: B
        get() = requireNotNull(_binding) { "Binding is either not initialized yet or already destroyed." }

    override fun B.initialize() {}

    override fun generateBinding(inflater: LayoutInflater, viewGroup: ViewGroup?, lifecycleOwner: LifecycleOwner) {
        _binding = bindingClass.inflate(inflater, viewGroup, false).apply {
            if (this is ViewDataBinding)
                this.lifecycleOwner = lifecycleOwner

            initialize()
        }
        lifecycleOwner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun destroyBinding(){
        _binding = null
    }
}