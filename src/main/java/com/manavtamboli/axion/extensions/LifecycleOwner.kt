package com.manavtamboli.axion.extensions

import android.content.Context
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

private class InvalidLifecycleOwnerException : Exception("Invalid LifecycleOwner. Must be a Fragment or ComponentActivity")

internal val LifecycleOwner.context : Context get() = when (this){
    is Fragment -> requireContext()
    is ComponentActivity -> this
    else -> throw InvalidLifecycleOwnerException()
}

internal val LifecycleOwner.window : Window get() = when(this) {
    is Fragment -> requireActivity().window
    is ComponentActivity -> window
    else -> throw InvalidLifecycleOwnerException()
}