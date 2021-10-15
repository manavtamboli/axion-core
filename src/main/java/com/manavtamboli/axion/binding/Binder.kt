package com.manavtamboli.axion.binding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding

/**
 * Convenience class to help implement [BindingComponent].
 * */
class Binder<B : ViewBinding> (private val bindingClass: Class<B>) : DefaultLifecycleObserver {

    private var _binding: B? = null
    val binding : B get() = requireNotNull(_binding) { "Binding is either not initialized yet or already destroyed." }

    fun generateBinding(inflater: LayoutInflater, viewGroup: ViewGroup?, lifecycleOwner: LifecycleOwner){
        _binding = bindingClass.inflate(inflater, viewGroup, false).apply {
            if (this is ViewDataBinding)
                this.lifecycleOwner = lifecycleOwner
        }
        lifecycleOwner.lifecycle.addObserver(this)
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        _binding = null
    }
}