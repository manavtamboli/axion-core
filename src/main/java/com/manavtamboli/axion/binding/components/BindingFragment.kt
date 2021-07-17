package com.manavtamboli.axion.binding.components

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.manavtamboli.axion.binding.Binder
import com.manavtamboli.axion.binding.BindingComponent
import com.manavtamboli.axion.binding.BindingComponent.Companion.generateBinding

open class BindingFragment<B : ViewBinding>(bindingClass: Class<B>) : Fragment(), BindingComponent<B> {

    final override val binder = Binder.ofClass(bindingClass)
    override fun B.initialize() {}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) = generateBinding(inflater, container)

}