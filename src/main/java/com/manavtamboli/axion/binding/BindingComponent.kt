package com.manavtamboli.axion.binding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

/**
 * Interface to generate bindings.
 * */
interface BindingComponent<B : ViewBinding> {

    /**
     * Property to access the generated binding.
     * */
    val binding : B

    /**
     * Called after binding is generated.
     * Override this to do things like initializing views, etc.
     * */
    fun B.initialize() {  }

    /**
     * Generates the binding.
     * */
    fun generateBinding(inflater: LayoutInflater, viewGroup: ViewGroup?, lifecycleOwner: LifecycleOwner)
}