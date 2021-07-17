package com.manavtamboli.axion.ui

import android.view.Window
import androidx.activity.ComponentActivity
import androidx.annotation.ColorRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import com.manavtamboli.axion.core.Arc
import com.manavtamboli.axion.core.Arc.Companion.context
import com.manavtamboli.axion.lifecycle.doOnStart

private fun LifecycleOwner.getWindow(): Window? {
    return when(this) {
        is Fragment -> requireActivity().window
        is ComponentActivity -> window
        else -> throw Exception("Invalid Lifecycle Owner. Must be a Fragment or Activity")
    }
}

fun LifecycleOwner.navColor(@ColorRes color : Int){
    doOnStart {
        getWindow()?.navigationBarColor = Arc.require().context.getColor(color)
    }
}

fun LifecycleOwner.statusColor(@ColorRes color: Int) {
    doOnStart {
        getWindow()?.statusBarColor = Arc.require().context.getColor(color)
    }
}

fun LifecycleOwner.systemColor(@ColorRes color: Int){
    doOnStart {
        val colorInt = Arc.require().context.getColor(color)
        getWindow()?.apply {
            navigationBarColor = colorInt
            statusBarColor = colorInt
        }
    }
}