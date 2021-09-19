package com.manavtamboli.axion.binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding


open class BindingActivity<B : ViewBinding>(bindingClass : Class<B>) : AppCompatActivity(), BindingComponent<B> by BindingComponentImpl(bindingClass) {


    /**
     * Property to access the generated binding.
     *
     * Must be accessed after [onCreate].
     * */
    final override val binding: B = super.binding


    final override fun generateBinding(inflater: LayoutInflater, viewGroup: ViewGroup?, lifecycleOwner: LifecycleOwner) {
        super.generateBinding(inflater, viewGroup, lifecycleOwner)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        generateBinding(layoutInflater, null, this)
        setContentView(binding.root)
    }
}