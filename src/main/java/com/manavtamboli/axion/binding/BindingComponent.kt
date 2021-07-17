package com.manavtamboli.axion.binding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

interface BindingComponent <B : ViewBinding> {

    val binder : Binder<B>
    fun B.initialize()

    companion object {

        val <B : ViewBinding> BindingComponent<B>.binding get() = binder.binding

        fun <B : ViewBinding> BindingComponent<B>.generateBinding(inflater: LayoutInflater, viewGroup: ViewGroup?): View {
            val lifecycleOwner = when(this){
                is Fragment -> viewLifecycleOwner
                is ComponentActivity -> this
                else -> throw IllegalArgumentException("Binding Component must be a Fragment or an Activity")
            }
            return binder.generateBinding(inflater, viewGroup, lifecycleOwner)
                .apply { initialize() }
                .root
        }
    }
}