package com.manavtamboli.axion.binding

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent
import androidx.viewbinding.ViewBinding

class Binder<T : ViewBinding> private constructor(private val bindingClass : Class<T>) : LifecycleObserver {

    private var _binding : T? = null
    val binding: T get() = requireNotNull(_binding){ "Binding is not yet initialized." }

    fun generateBinding(inflater: LayoutInflater, viewGroup: ViewGroup?, lifecycleOwner: LifecycleOwner): T {
        return bindingClass.inflate(inflater, viewGroup, false).also {
            _binding = it
            if (it is ViewDataBinding) it.lifecycleOwner = lifecycleOwner
            lifecycleOwner.lifecycle.addObserver(this)
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun destroyBinding() { _binding = null }

    companion object {
        @JvmStatic
        fun <T : ViewBinding> ofClass(bindingClass: Class<T>) = Binder(bindingClass)

        @Suppress("UNCHECKED_CAST")
        fun <B : ViewBinding> Class<B>.inflate(inflater: LayoutInflater, viewGroup: ViewGroup?, attachToParent : Boolean) : B {
            return getDeclaredMethod("inflate", LayoutInflater::class.java, ViewGroup::class.java, Boolean::class.java)
                .invoke(null, inflater, viewGroup, attachToParent) as B
        }
    }
}