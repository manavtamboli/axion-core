package com.manavtamboli.axion.binding.components

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.manavtamboli.axion.binding.Binder
import com.manavtamboli.axion.binding.BindingComponent
import com.manavtamboli.axion.binding.BindingComponent.Companion.generateBinding

open class BindingActivity<B : ViewBinding>(bindingClass : Class<B>) : AppCompatActivity(), BindingComponent<B> {

    final override val binder = Binder.ofClass(bindingClass)
    override fun B.initialize() {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(generateBinding(layoutInflater, null))
    }
}