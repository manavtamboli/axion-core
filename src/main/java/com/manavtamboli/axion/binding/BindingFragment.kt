package com.manavtamboli.axion.binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding


open class BindingFragment<B : ViewBinding>(bindingClass: Class<B>) : Fragment(), BindingComponent<B> by BindingComponentImpl(bindingClass) {

    /**
     * Property to access the generated binding.
     *
     * Must be accessed starting from [onCreateView] and valid upto [onDestroyView].
     * */
    final override val binding: B get() = super.binding


    final override fun generateBinding(inflater: LayoutInflater, viewGroup: ViewGroup?, lifecycleOwner: LifecycleOwner) {
        super.generateBinding(inflater, viewGroup, lifecycleOwner)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        generateBinding(inflater, container, viewLifecycleOwner)
        return binding.root
    }
}