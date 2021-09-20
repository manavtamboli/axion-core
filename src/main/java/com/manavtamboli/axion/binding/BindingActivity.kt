package com.manavtamboli.axion.binding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LifecycleOwner
import androidx.viewbinding.ViewBinding
import com.manavtamboli.axion.extensions.log

open class BindingActivity<B : ViewBinding>(bindingClass : Class<B>) : AppCompatActivity(), BindingComponent<B> {

    private val binder = Binder(bindingClass)

    /**
     * Property to access the generated binding.
     *
     * Must be accessed after [onCreate].
     * */
    final override val binding get() = binder.binding

    final override fun generateBinding(
        inflater: LayoutInflater,
        viewGroup: ViewGroup?,
        lifecycleOwner: LifecycleOwner
    ) {
        binder.generateBinding(inflater, viewGroup, lifecycleOwner)
        binding.initialize()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        generateBinding(layoutInflater, null, this)
        setContentView(binding.root)
    }
}

