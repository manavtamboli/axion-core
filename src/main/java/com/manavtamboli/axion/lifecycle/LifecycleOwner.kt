package com.manavtamboli.axion.lifecycle

import android.content.Context
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.contract.ActivityResultContract
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

private class InvalidLifecycleOwnerException : Exception("Invalid LifecycleOwner. Must be a Fragment or ComponentActivity")

internal val LifecycleOwner.activity : ComponentActivity get() = when (this){
    is Fragment -> requireActivity()
    is ComponentActivity -> this
    else -> throw InvalidLifecycleOwnerException()
}

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

internal fun <I, O> LifecycleOwner.getLauncher(contract : ActivityResultContract<I, O>, callback : ActivityResultCallback<O>) = when (this){
    is Fragment -> registerForActivityResult(contract, callback)
    is ComponentActivity -> registerForActivityResult(contract, callback)
    else -> throw InvalidLifecycleOwnerException()
}