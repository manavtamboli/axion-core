package com.manavtamboli.axion.extensions

import android.content.Context
import androidx.activity.ComponentActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner

private class InvalidLifecycleOwnerException : Exception("Invalid LifecycleOwner. Must be a Fragment or ComponentActivity")

internal val LifecycleOwner.context : Context get() = when (this){
    is Fragment -> requireContext()
    is ComponentActivity -> this
    else -> throw InvalidLifecycleOwnerException()
}