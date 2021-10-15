package com.manavtamboli.axion.binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


open class BindingFragment<B : ViewBinding>(bindingClass: Class<B>) : Fragment(), BindingComponent<B> {

    private val binder = Binder(bindingClass)

    /**
     * Property to access the generated binding.
     *
     * Must be accessed starting from [onCreateView] and valid upto [onDestroyView].
     * */
    final override val binding get() = binder.binding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binder.generateBinding(inflater, container, viewLifecycleOwner)
        binding.initialize()
        return binding.root
    }
}