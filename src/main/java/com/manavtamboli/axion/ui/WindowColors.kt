package com.manavtamboli.axion.ui

import androidx.annotation.ColorRes
import androidx.lifecycle.LifecycleOwner
import com.manavtamboli.axion.core.Arc
import com.manavtamboli.axion.core.Arc.Companion.applicationContext
import com.manavtamboli.axion.lifecycle.onStart
import com.manavtamboli.axion.lifecycle.window

fun LifecycleOwner.navBarColor(@ColorRes color : Int){
    onStart {
        window.navigationBarColor = Arc.require().applicationContext.getColor(color)
    }
}

fun LifecycleOwner.statusBarColor(@ColorRes color: Int) {
    onStart {
        window.statusBarColor = Arc.require().applicationContext.getColor(color)
    }
}

fun LifecycleOwner.systemBarsColor(@ColorRes color: Int){
    onStart {
        val colorInt = Arc.require().applicationContext.getColor(color)
        window.apply {
            navigationBarColor = colorInt
            statusBarColor = colorInt
        }
    }
}