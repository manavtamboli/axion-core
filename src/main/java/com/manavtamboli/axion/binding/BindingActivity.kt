package com.manavtamboli.axion.binding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

open class BindingActivity<B : ViewBinding>(bindingClass : Class<B>) : AppCompatActivity(), BindingComponent<B> {

    private val binder = Binder(bindingClass)

    /**
     * Property to access the generated binding.
     *
     * Must be accessed after [onCreate].
     * */
    final override val binding get() = binder.binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binder.generateBinding(layoutInflater, null, this)
        binding.initialize()
        setContentView(binding.root)
    }
}